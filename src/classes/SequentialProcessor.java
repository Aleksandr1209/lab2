package classes;

public class SequentialProcessor {
    public void process(int[] array, int multiplier, int taskType) {
        for (int i = 0; i < array.length; i++) {
            array[i] = switch (taskType) {
                case 1 -> ComplexOperations.factorial(array[i]);
                case 2 -> ComplexOperations.fibonacci(array[i]);
                case 3 -> ComplexOperations.power(array[i], multiplier);
                default -> array[i] * multiplier;
            };
        }
    }
}
