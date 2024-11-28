package classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelProcessor {
    public void process(int[] array, int multiplier, int numThreads, int taskType) {
        int chunkSize = (int) Math.ceil((double) array.length / numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, array.length);

            executor.execute(() -> {
                for (int j = start; j < end; j++) {
                    array[j] = switch (taskType) {
                        case 1 -> ComplexOperations.factorial(array[j]);
                        case 2 -> ComplexOperations.fibonacci(array[j]);
                        case 3 -> ComplexOperations.power(array[j], multiplier);
                        default -> array[j] * multiplier;
                    };
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    public void processUneven(int[] array, int[] elementsPerThread, int multiplier, int taskType) {
        ExecutorService executor = Executors.newFixedThreadPool(elementsPerThread.length);

        int currentIndex = 0;
        for (int i = 0; i < elementsPerThread.length; i++) {
            final int start = currentIndex;
            final int count = elementsPerThread[i];
            currentIndex += count;

            executor.execute(() -> {
                for (int j = start; j < start + count && j < array.length; j++) {
                    array[j] = switch (taskType) {
                        case 1 -> ComplexOperations.factorial(array[j]);
                        case 2 -> ComplexOperations.fibonacci(array[j]);
                        case 3 -> ComplexOperations.power(array[j], multiplier);
                        default -> array[j] * multiplier;
                    };
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
