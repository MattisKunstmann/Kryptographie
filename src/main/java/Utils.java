import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    public static void createFile(String name, String content) throws IOException {
        FileWriter writer = new FileWriter(name);
        writer.write(String.valueOf(content));
        writer.close();
    }

    public static String readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String text = "";
        while (scanner.hasNext()) {
            text += scanner.nextLine();
        }
        return text;
    }
}
