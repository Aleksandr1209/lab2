package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static int[] readFileToArray(String filename, int size) {
        int[] array = new int[size];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                array[index++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
