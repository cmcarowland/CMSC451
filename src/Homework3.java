import java.util.Random;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.PriorityQueue;

class Homework3
{
    public static void main(String[] args) {
        if(args.length == 0){
           System.out.println("Usage: Please select SPQ for Sorted Priority Queue or PQ for Priority Queue\njava -cp bin Homework SPQ\njava -cp bin Homework PQ\n");
        } else {
            if (args[0].equals("PQ"))
                RunPQTest();
            else if(args[0].equals("SPQ"))
                RunSPQTest();
        }
    }

    static private void printHeader(String testName) {
        System.out.println(testName + " SPEED TESTS");
        // Timing across input sizes
        System.out.printf("%n%-10s%-20s%n", "n", "time (ns)");
        System.out.println("--------------------------");
    }

    static private void printFooter(int n, long average) {
        System.out.printf("%-10d%-20d\n", n, average);
    }

    static void RunPQTest() {
        // Warm up the JIT — results discarded
        int[] warmupSizes = {100, 1000, 10000, 100000};
        for (int i = 0; i < 3; i++)
            for (int n : warmupSizes) {
                PriorityQueue<Integer> queue = new PriorityQueue<>(n);
                sort(queue, randomArray(n));
            }

        printHeader("PriorityQueue");
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int trials = n < 1000 ? 1000 : 10;
            long total = 0;
            for (int t = 0; t < trials; t++) {
                int[] input = randomArray(n);
                long start = System.nanoTime();
                PriorityQueue<Integer> queue = new PriorityQueue<>(input.length);
                sort(queue, input);
                total += System.nanoTime() - start;
            }
            long avg = total / trials;
            printFooter(n, avg);
        }
    }

    static void RunSPQTest() {
        // Warm up the JIT — results discarded
        int[] warmupSizes = {100, 1000, 10000, 100000};
        for (int i = 0; i < 3; i++)
            for (int n : warmupSizes) {
                SortedPriorityQueue queue = new SortedPriorityQueue(n);
                sort(queue, randomArray(n));
            }

        printHeader("SortedPriorityQueue");
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int trials = n < 1000 ? 1000 : 10;
            long total = 0;
            for (int t = 0; t < trials; t++) {
                int[] input = randomArray(n);
                long start = System.nanoTime();
                SortedPriorityQueue queue = new SortedPriorityQueue(input.length);
                sort(queue, input);
                total += System.nanoTime() - start;
            }
            long avg = total / trials;
            printFooter(n, avg);
        }
    }

    static void sort(PriorityQueue<Integer> queue, int[] array) {
        for (int v : array)
            queue.add(v);
        for (int i = 0; i < array.length; i++)
            queue.remove();
    }

    static void sort(SortedPriorityQueue queue, int[] array) {
        for (int v : array)
            queue.add(v);
        for (int i = 0; i < array.length; i++)
            queue.remove();
    }

    static int[] randomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100000);
        return arr;
    }
} 