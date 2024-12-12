import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileLoader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();

        System.out.print("Введите директорию: ");
        String directory = scanner.nextLine();

        System.out.print("Введите текст для сохранения: ");
        String text = scanner.nextLine();

        if (validateInput(fileName, directory, text)) {
            saveFile(fileName, directory, text);
        }

        System.out.print("Введите имя файла для поиска: ");
        String searchFileName = scanner.nextLine();

        System.out.print("Введите директорию для поиска: ");
        String searchDirectory = scanner.nextLine();

        String fileContent = searchFile(searchFileName, searchDirectory);
        if (fileContent != null) {
            System.out.println("Содержимое файла:");
            System.out.println(fileContent);
        } else {
            System.out.println("Файл не найден.");
        }
    }

    private static boolean validateInput(String fileName, String directory, String text) {
        if (fileName.isEmpty() && directory.isEmpty() && text.isEmpty()) {
            System.out.println("Ошибка: введены пустые данные.");
            return false;
        }
        return true;
    }

    private static void saveFile(String fileName, String directory, String text) {
        try {
            File file = new File(directory, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Files.write(Paths.get(directory, fileName), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            LocalDateTime ldt = LocalDateTime.now();
            System.out.println("Файл сохранен. Размер файла: " + file.length() + " байт. Время записи: " + ldt);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private static String searchFile(String fileName, String directory) {
        try {
            if (Files.exists(Paths.get(directory, fileName))) {
                return new String(Files.readAllBytes(Paths.get(directory, fileName)));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при поиске файла: " + e.getMessage());
        }
        return null;
    }
}