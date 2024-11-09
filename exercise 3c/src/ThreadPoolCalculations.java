import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class ThreadPoolCalculations {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交质数计算任务
        for (int i = 1; i <= 25; i++) {
            int num = i;
            executor.submit(() -> {
                System.out.printf("[%d] Thread %s - Prime #%d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), num, calculatePrime(num));
                randomDelay();
            });
        }

        // 提交斐波那契计算任务
        for (int i = 1; i <= 50; i++) {
            int num = i;
            executor.submit(() -> {
                System.out.printf("[%d] Thread %s - Fibonacci #%d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), num, calculateFibonacci(num));
                randomDelay();
            });
        }

        // 提交阶乘计算任务
        for (int i = 1; i <= 50; i++) {
            int num = i;
            executor.submit(() -> {
                System.out.printf("[%d] Thread %s - Factorial of %d: %d%n", System.currentTimeMillis(), Thread.currentThread().getName(), num, calculateFactorial(num));
                randomDelay();
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100); // 延迟 100-500 毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int calculatePrime(int n) {
        int count = 0, num = 2;
        while (count < n) {
            if (isPrime(num)) count++;
            if (count < n) num++;
        }
        return num;
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static long calculateFibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a;
            a = b;
            b = temp + b;
        }
        return b;
    }

    static long calculateFactorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
