import classes.*;

public class Main {
    public static void main(String[] args) {
        ConfigReader config = new ConfigReader("config.properties");

        // Чтение параметров из конфиг-файла
        int N = config.getInt("N");
        int multiplier = config.getInt("multiplier");
        int taskType = config.getTaskType();
        int M = config.getInt("M");
        String inputFile = config.getString("inputFile");
        int[] elementsPerThread = config.getElementsPerThread();

        // Генерация данных
        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generate(N, inputFile);

        // Чтение данных в массив
        int[] array = Utils.readFileToArray(inputFile, N);
        int[] arrayCopy = array.clone();

        SequentialProcessor sequentialProcessor = new SequentialProcessor();
        ParallelProcessor parallelProcessor = new ParallelProcessor();

        // 1. Простая задача (умножение) в последовательном режиме
        long startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, 0);
        long endTime = System.nanoTime();
        System.out.println("Простая задача (последовательная): " + (endTime - startTime) + " нс");

        // 2. Простая задача (умножение) в многопоточном режиме
        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, M, 0);
        endTime = System.nanoTime();
        System.out.println("Простая задача (многопоточная): " + (endTime - startTime) + " нс");

        // 3. Сложная задача в последовательном режиме
        array = arrayCopy.clone();
        startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (последовательная): " + (endTime - startTime) + " нс");

        // 4. Сложная задача в многопоточном режиме
        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, M, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (многопоточная): " + (endTime - startTime) + " нс");

        // 5. Сложная задача с неравномерной обработкой
        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.processUneven(array, elementsPerThread, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (неравномерная многопоточная): " + (endTime - startTime) + " нс");
    }
}
