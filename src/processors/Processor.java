package processors;

import operations.TaskType;

public interface Processor {
    void process(int[] array, int multiplier, TaskType taskType);
}