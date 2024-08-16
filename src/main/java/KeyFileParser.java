import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KeyFileParser {

    public List<String> parseFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}