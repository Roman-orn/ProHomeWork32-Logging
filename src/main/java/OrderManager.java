import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class OrderManager {

    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    private final LinkedHashMap<Long, Order> orders = new LinkedHashMap<>();
    private final AtomicLong counterId = new AtomicLong(1);

    public Logger getLogger() {
        return logger;
    }

    public Long add(Order order) {
        if (order == null) {
            IllegalArgumentException ex = new IllegalArgumentException("The order cannot be null.");
            logger.error("Critical error when adding the order = null", ex);
            throw ex;
        }
        Long orderId = counterId.getAndIncrement();
        order.setId(orderId);
        orders.put(orderId, order);
        logger.info("Order No.{} has been accepted", orderId);
        return orderId;
    }

    public Optional<Order> deliver() {
        if (orders.isEmpty()) {
            logger.warn("There are no orders.");
            return Optional.empty();
        }
        Long firstOrderId = orders.keySet().iterator().next();
        Order order = orders.remove(firstOrderId);
        logger.info("Order No.{} has been issued.", order.getId());
        return Optional.of(order);
    }

    public Optional<Order> deliver(Long orderId) {
        if (!orders.containsKey(orderId)) {
            logger.warn("Order No.{} is missing.", orderId);
            return Optional.empty();
        }
        Order order = orders.remove(orderId);
        logger.info("Order No.{} has been issued.", order.getId());
        return Optional.of(order);
    }

    public void draw() {
        if (orders.isEmpty()) {
            System.out.println("There are no orders in the queue.");
            return;
        }

        System.out.println("Order queue:");
        for (Map.Entry entry : orders.entrySet()) {
            System.out.printf("%d - %s\n", entry.getKey(), ((Order) entry.getValue()).getName());
        }
    }
}
