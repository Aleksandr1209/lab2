package processors;

import operations.TaskType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelProcessor implements Processor {
    private final int numThreads;

    public ParallelProcessor(int numThreads) {
        this.numThreads = numThreads;
    }

    @Override
    public void process(int[] array, int multiplier, TaskType taskType) {
        processWithDistribution(array, null, multiplier, taskType);
    }

    public void processUneven(int[] array, int[] elementsPerThread, int multiplier, TaskType taskType) {
        processWithDistribution(array, elementsPerThread, multiplier, taskType);
    }

    private void processWithDistribution(int[] array, int[] elementsPerThread, int multiplier, TaskType taskType) {
        ExecutorService executor = elementsPerThread == null
                ? Executors.newFixedThreadPool(numThreads)
                : Executors.newFixedThreadPool(elementsPerThread.length);

        int currentIndex = 0;
        int totalThreads = elementsPerThread == null ? numThreads : elementsPerThread.length;
        int chunkSize = elementsPerThread == null ? (int) Math.ceil((double) array.length / numThreads) : 0;

        for (int threadIndex = 0; threadIndex < totalThreads; threadIndex++) {
            final int start = currentIndex;
            final int count = elementsPerThread == null ? chunkSize : elementsPerThread[threadIndex];
            currentIndex += count;

            executor.execute(() -> {
                for (int j = start; j < Math.min(start + count, array.length); j++) {
                    array[j] = taskType.apply(array[j], multiplier);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}