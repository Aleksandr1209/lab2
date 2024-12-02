package classes;

public class ComplexOperations {
    public static int fibonacci(int n) {
        if (n == 1) return 1;
        int prev1 = 0, prev2 = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return current;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int power(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }
}
