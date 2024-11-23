package lab.Serialization;

import lab.Model.Order;
import lab.Model.OrderRepository;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;

public class YamlSerializationHelper implements ISerializer{
    private final String FILE_EXTENSION = ".yaml";

    public void serialize(OrderRepository orderRepository, String filepath) throws IOException {
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);
        try (Writer writer = new FileWriter(filepath + FILE_EXTENSION)) {
            yaml.dump(orderRepository.getAllOrders(), writer);
        }
    }

    public OrderRepository deserialize(String filepath) throws IOException {

        Yaml yaml = new Yaml();
        try (Reader reader = new FileReader(filepath + FILE_EXTENSION)) {
            List<Order> orders = yaml.load(reader);

            return new OrderRepository(orders);
        }
    }
}
