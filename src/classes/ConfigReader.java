package classes;

import java.io.FileInputStream;
import java.io.IOException;
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

    public int getTaskType() {
        return Integer.parseInt(properties.getProperty("taskType"));
    }

    public int getUnevenThreads() {
        return Integer.parseInt(properties.getProperty("unevenThreads"));
    }

    public int[] getElementsPerThread() {
        String[] parts = properties.getProperty("elementsPerThread").split(",");
        int[] elements = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            elements[i] = Integer.parseInt(parts[i]);
        }
        return elements;
    }
}
