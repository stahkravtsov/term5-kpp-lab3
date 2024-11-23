package lab;

import com.github.javafaker.Faker;
import lab.Model.Client;
import lab.Model.Dish;
import lab.Model.Order;
import lab.Model.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryGenerator {
    private static final int NUMBER_OF_ORDERS = 10;
    private static final int MIN_DISHES_PER_ORDER = 1;
    private static final int MAX_DISHES_PER_ORDER = 5;
    private final Faker faker;

    public OrderRepositoryGenerator() {
        this.faker = new Faker();
    }

    public OrderRepository generateOrderRepository() {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_ORDERS; i++) {
            int orderNumber = i + 1;
            Client client = new Client(faker.name().fullName(), "" , faker.internet().emailAddress());
            List<Dish> dishes = generateDishes(faker.number().numberBetween(MIN_DISHES_PER_ORDER, MAX_DISHES_PER_ORDER));

            Order order = new Order(orderNumber, client, dishes);
            orders.add(order);
        }

        return new OrderRepository(orders);
    }

    private List<Dish> generateDishes(int numberOfDishes) {
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < numberOfDishes; i++) {
            String dishName = faker.food().dish();
            double price = faker.number().randomDouble(2, 5, 50);
            dishes.add(new Dish(dishName, price));
        }
        return dishes;
    }
}
