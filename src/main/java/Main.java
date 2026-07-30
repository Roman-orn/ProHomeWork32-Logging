import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static void main(String[] args) {

        OrderManager manager = new OrderManager();

        try {
            Long orderId1 = manager.add(new Order("John", 50));
            Long orderId2 = manager.add(new Order("Alice", 100));
            Long orderId3 = manager.add(new Order("Bob", 150));
            manager.draw();

            manager.deliver();
            manager.deliver(orderId3);
            manager.deliver(5L);

            manager.draw();

            manager.add(null);
        } catch (IllegalArgumentException ex) {
            log.error("Order processing error: {}", ex.getMessage(), ex);
        }

    }
}
