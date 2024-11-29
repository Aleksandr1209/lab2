import classes.*;

public class Main {
    public static void main(String[] args) {
        ConfigReader config = new ConfigReader("config.properties");

        int N = config.getInt("N");
        int multiplier = config.getInt("multiplier");
        int taskType = config.getTaskType();
        int M = config.getInt("M");
        String inputFile = config.getString("inputFile");
        int[] elementsPerThread = config.getElementsPerThread();

        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generate(N, inputFile);

        int[] array = Utils.readFileToArray(inputFile, N);
        int[] arrayCopy = array.clone();

        SequentialProcessor sequentialProcessor = new SequentialProcessor();
        ParallelProcessor parallelProcessor = new ParallelProcessor();

        long startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, 0);
        long endTime = System.nanoTime();
        System.out.println("Простая задача (последовательная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, M, 0);
        endTime = System.nanoTime();
        System.out.println("Простая задача (многопоточная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (последовательная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, M, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (многопоточная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.processUneven(array, elementsPerThread, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (неравномерная многопоточная): " + (endTime - startTime) + " нс");
    }
}
