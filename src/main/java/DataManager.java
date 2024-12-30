import java.io.*;
import java.util.List;

public class DataManager {
    public static void saveData(List<Customer> customers, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
        }
    }

    public static List<Customer> loadData(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Customer>) ois.readObject();
        }
    }
}
