package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {
    private String filename;

    public FileReaderUtil(String filename) {
        this.filename = filename;
    }

    public int[] readFileToArray(int size) {
        int[] array = new int[size];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (index < size) {
                    array[index++] = Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
