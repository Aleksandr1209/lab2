import operations.TaskType;
import processors.*;
import utils.*;

public class Main {
    public static void main(String[] args) {
        ConfigReader config = new ConfigReader("config.properties");

        int N = config.getInt("N");
        int multiplier = config.getInt("multiplier");
        String inputFile = config.getString("inputFile");
        int numThreads = config.getInt("M");
        int[] elementsPerThread = config.getIntArray("elementsPerThread");

        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generate(N, inputFile);
        FileReaderUtil fileReaderUtil = new FileReaderUtil("numbers.txt");

        int[] array = fileReaderUtil.readFileToArray(N);
        int[] arrayCopy = array.clone();

        Processor sequentialProcessor = new SequentialProcessor();
        ParallelProcessor parallelProcessor = new ParallelProcessor(numThreads);

        long startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, TaskType.MULTIPLY);
        long endTime = System.nanoTime();
        System.out.println("Простая задача (последовательная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, TaskType.MULTIPLY);
        endTime = System.nanoTime();
        System.out.println("Простая задача (многопоточная): " + (endTime - startTime) + " нс");

        int taskTypeIndex = config.getInt("taskType");
        TaskType taskType = TaskType.values()[taskTypeIndex];

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        sequentialProcessor.process(array, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (последовательная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.process(array, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (многопоточная): " + (endTime - startTime) + " нс");

        array = arrayCopy.clone();
        startTime = System.nanoTime();
        parallelProcessor.processUneven(array, elementsPerThread, multiplier, taskType);
        endTime = System.nanoTime();
        System.out.println("Сложная задача (неравномерная многопоточная): " + (endTime - startTime) + " нс");
    }
}