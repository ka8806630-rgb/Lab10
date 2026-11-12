
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void logAction(String action) {
        try (FileWriter writer = new FileWriter("actions.txt", true)) {
            writer.write(action + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }
}
