import lab.Model.Client;
import lab.Model.Dish;
import lab.Model.Order;
import lab.Model.OrderRepository;
import lab.OrderRepositoryGenerator;
import lab.Serialization.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filepath = "orders";
        OrderRepository repository = new OrderRepository();

        List<ISerializer> serializers = new ArrayList<>();
        serializers.add(new YamlSerializationHelper());
        serializers.add(new JsonSerializationHelper());
        serializers.add(new JavaSerializationHelper());
        serializers.add(new OrderRepositoryStorage());

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("g")) {
                repository = new OrderRepositoryGenerator().generateOrderRepository();
            }

            if (input.equals("e")) {
                return;
            }

            if (input.equals("s")) {
                for (ISerializer serializer : serializers) {
                    serializer.serialize(repository, filepath);
                }
            }

            if (input.equals("p")) {
                String type = scanner.nextLine();

                switch (type) {
                    case "java":
                        System.out.println("Order Repository" + serializers.get(2).deserialize(filepath));
                        break;
                    case "json":
                        System.out.println("Order Repository" + serializers.get(1).deserialize(filepath));
                        break;

                    case "yaml":
                        System.out.println("Order Repository" + serializers.getFirst().deserialize(filepath));
                        break;

                    case "txt":
                        System.out.println("Order Repository" + serializers.get(3).deserialize(filepath));
                        break;
                }

            }

        }
    }

}
