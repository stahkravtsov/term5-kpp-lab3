package lab.Serialization;

import lab.Model.Order;
import lab.Model.OrderRepository;

import java.io.*;

public class JavaSerializationHelper implements ISerializer {

    public void serialize(OrderRepository order, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(order);
        }
    }

    public OrderRepository deserialize(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (OrderRepository) ois.readObject();
        }
    }
}
