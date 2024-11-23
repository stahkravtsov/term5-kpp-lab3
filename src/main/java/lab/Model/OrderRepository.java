package lab.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Order> orders = new ArrayList<>();

    public OrderRepository() { }
    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order findOrderById(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    public boolean removeOrderById(int orderNumber) {
        return orders.removeIf(order -> order.getOrderNumber() == orderNumber);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public void saveOrdersToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(orders);
        }
    }

    public void loadOrdersFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            orders = (List<Order>) ois.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Order order : orders) {
            sb.append(order.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
