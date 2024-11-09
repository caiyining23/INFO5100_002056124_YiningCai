import java.util.Random;

public class MultiThreadedCalculations {

    public static void main(String[] args) {
        Thread primeThread = new Thread(new PrimeCalculator(), "Prime Thread");
        Thread fibonacciThread = new Thread(new FibonacciCalculator(), "Fibonacci Thread");
        Thread factorialThread = new Thread(new FactorialCalculator(), "Factorial Thread");

        primeThread.start();
        fibonacciThread.start();
        factorialThread.start();
    }

    static void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100); // 延迟 100-500 毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class PrimeCalculator implements Runnable {
        @Override
        public void run() {
            int count = 0;
            int num = 2;
            while (count < 25) {
                if (isPrime(num)) {
                    System.out.printf("[%d] %s - Prime #%d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), count + 1, num);
                    count++;
                    randomDelay();
                }
                num++;
            }
        }

        boolean isPrime(int n) {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    static class FibonacciCalculator implements Runnable {
        @Override
        public void run() {
            long a = 0, b = 1;
            for (int i = 1; i <= 50; i++) {
                System.out.printf("[%d] %s - Fibonacci #%d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), i, a);
                long temp = a;
                a = b;
                b = temp + b;
                randomDelay();
            }
        }
    }

    static class FactorialCalculator implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                System.out.printf("[%d] %s - Factorial of %d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), i, factorial(i));
                randomDelay();
            }
        }

        long factorial(int n) {
            long result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
}
