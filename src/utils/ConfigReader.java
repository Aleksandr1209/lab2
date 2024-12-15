package utils;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader(String configFile) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int[] getIntArray(String key) {
        String[] parts = properties.getProperty(key).split(",");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }
}