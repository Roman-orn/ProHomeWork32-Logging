import java.util.Objects;

public class Order {

    private Long orderId;
    private String name;
    private double total;

    public Order(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public Long getId() {
        return orderId;
    }

    public void setId(Long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        return Objects.equals(orderId, ((Order) o).orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public String toString() {
        return "Order {id=%d, name=%s, total=%.2f}".formatted(orderId, name, total);
    }
}
