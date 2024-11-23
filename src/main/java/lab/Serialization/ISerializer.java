package lab.Serialization;

import lab.Model.OrderRepository;

import java.io.IOException;

public interface ISerializer {
    void serialize(OrderRepository repository, String filename) throws IOException;
    OrderRepository deserialize(String filename) throws IOException, ClassNotFoundException;
}
