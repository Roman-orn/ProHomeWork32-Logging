import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class OrderManager {

    private static final Logger log = LoggerFactory.getLogger(OrderManager.class);

    private final LinkedHashMap<Long, Order> orders = new LinkedHashMap<>();
    private final AtomicLong counterId = new AtomicLong(1);

    public Long add(Order order) {
        if (order == null) {
            log.error("Order is null");
            throw new IllegalArgumentException("Order cannot be null");
        }
        Long orderId = counterId.getAndIncrement();
        order.setId(orderId);
        orders.put(orderId, order);
        log.info("Order No.{} has been accepted", orderId);
        return orderId;
    }

    public Optional<Order> deliver() {
        if (orders.isEmpty()) {
            log.warn("There are no orders.");
            return Optional.empty();
        }
        Long firstOrderId = orders.keySet().iterator().next();
        Order order = orders.remove(firstOrderId);
        log.info("Order No.{} has been issued.", order.getId());
        return Optional.of(order);
    }

    public Optional<Order> deliver(Long orderId) {
        if (!orders.containsKey(orderId)) {
            log.warn("Order No.{} is missing.", orderId);
            return Optional.empty();
        }
        Order order = orders.remove(orderId);
        log.info("Order No.{} has been issued.", order.getId());
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
