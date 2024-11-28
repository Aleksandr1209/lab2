package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public void generate(int n, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= n; i++) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
