package lab.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int orderNumber;
    private Client client;
    private transient List<Dish> dishes;
    private double totalPrice;

    public Order() {
        dishes = new ArrayList<>();
    }

    public Order(int orderNumber, Client client, List<Dish> dishes) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.dishes = dishes;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        totalPrice += dish.getPrice();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        this.totalPrice = calculateTotalPrice(); // Оновлюємо ціну після зміни страв
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dishes != null) {
            for (Dish dish : dishes) {
                sb.append(dish).append("\n");
            }
        }

        return "lab.Model.Order{" +
                "\norderNumber=" + orderNumber +
                ",\nclient=" + client +
                ",\ntotalPrice=" + totalPrice +
                ",\ndishes=" + (dishes != null ? dishes.size() : 0) +
                "\ndishes:\n" + sb.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, client, totalPrice);
    }
}
