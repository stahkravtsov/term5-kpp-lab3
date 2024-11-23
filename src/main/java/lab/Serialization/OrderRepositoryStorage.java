package lab.Serialization;

import lab.Model.Client;
import lab.Model.Dish;
import lab.Model.Order;
import lab.Model.OrderRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryStorage implements ISerializer {
    private final String FILE_EXTENSION = ".txt";

    public void serialize(OrderRepository repository, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + FILE_EXTENSION))) {
            for (Order order : repository.getAllOrders()) {
                writer.write("Order Number: " + order.getOrderNumber());
                writer.newLine();

                if (order.getClient() != null) {
                    writer.write("Client Name: " + order.getClient().getName());
                    writer.newLine();
                } else {
                    writer.write("Client: null");
                    writer.newLine();
                }

                writer.write("Dishes:");
                writer.newLine();
                if (order.getDishes() != null) {
                    for (Dish dish : order.getDishes()) {
                        writer.write("Dish: " + dish.getName() + ", Price: " + dish.getPrice());
                        writer.newLine();
                    }
                }
                writer.newLine(); // Розділюємо замовлення пустим рядком
            }
        }
    }

    public OrderRepository deserialize(String filePath) throws IOException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + FILE_EXTENSION))) {
            String line;
            Order currentOrder = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Order Number:")) {
                    if (currentOrder != null) {
                        orders.add(currentOrder);
                    }

                    currentOrder = new Order();
                    currentOrder.setOrderNumber(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Client Name:")) {
                    if (currentOrder != null) {
                        String clientName = line.split(": ")[1];
                        currentOrder.setClient(new Client(clientName));
                    }
                } else if (line.startsWith("Dish:")) {
                    if (currentOrder != null) {
                        String[] dishInfo = line.split(", ");
                        String dishName = dishInfo[0].split(": ")[1];
                        double dishPrice = Double.parseDouble(dishInfo[1].split(": ")[1]);
                        currentOrder.addDish(new Dish(dishName, dishPrice));
                    }
                }
            }

            // Додаємо останнє замовлення, якщо воно існує
            if (currentOrder != null) {
                orders.add(currentOrder);
            }
        }
        return new OrderRepository(orders);
    }
}
