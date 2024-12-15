package operations;

import java.util.function.BiFunction;

public enum TaskType {
    MULTIPLY((value, multiplier) -> value * multiplier),
    FACTORIAL((value, multiplier) -> {
        int result = 1;
        for (int i = 2; i <= value; i++) result *= i;
        return result;
    }),
    FIBONACCI((value, multiplier) -> {
        if (value <= 1) return 1;
        int prev1 = 0, prev2 = 1, current = 0;
        for (int i = 2; i <= value; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return current;
    }),
    POWER((value, multiplier) -> (int) Math.pow(value, multiplier));

    private final BiFunction<Integer, Integer, Integer> operation;

    TaskType(BiFunction<Integer, Integer, Integer> operation) {
        this.operation = operation;
    }

    public int apply(int value, int multiplier) {
        return operation.apply(value, multiplier);
    }
}