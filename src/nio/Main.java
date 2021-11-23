package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("start");

        Path path = Paths.get("file.txt");
        try {
            Files.write(path, "Привет NIO".getBytes());
            byte[] bytes = Files.readAllBytes(path);
            System.out.println(new String(bytes, "utf8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
