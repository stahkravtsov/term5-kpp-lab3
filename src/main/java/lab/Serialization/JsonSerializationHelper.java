package lab.Serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab.Model.Order;
import lab.Model.OrderRepository;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class JsonSerializationHelper implements ISerializer {
    private final String FILE_EXTENSION = ".json";

    private static final Gson gson = new GsonBuilder()
            .create();

    public void serialize(OrderRepository order, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath + FILE_EXTENSION)) {
            gson.toJson(order, writer);
        }
    }

    public OrderRepository deserialize(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath + FILE_EXTENSION)) {
            return gson.fromJson(reader, OrderRepository.class);
        }
    }
}
