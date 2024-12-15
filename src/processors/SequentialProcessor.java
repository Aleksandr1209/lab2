package processors;

import operations.TaskType;

public class SequentialProcessor implements Processor {
    @Override
    public void process(int[] array, int multiplier, TaskType taskType) {
        for (int i = 0; i < array.length; i++) {
            array[i] = taskType.apply(array[i], multiplier);
        }
    }
}
