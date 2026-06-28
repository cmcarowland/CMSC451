import java.util.Random;

class Homework3
{
    public static void main(String[] args) {
        // Warm up the JIT — results discarded
        for (int i = 0; i < 5; i++){
            SortedPriorityQueue queue = new SortedPriorityQueue(10000);
            sort(queue, randomArray(10000));
        }

        // Timing across input sizes
        System.out.println("\nn\t\ttime (ns)");
        System.out.println("-----------------------------");
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
                 if (!queue.isSorted()) {
                    System.out.println("Error: array is not sorted!");
                }
            }
            long avg = total / trials;
            System.out.println(n + "\t\t" + avg);
        }

    }

    static void sort(SortedPriorityQueue queue, int[] array)
    {
        for (int i = 0; i < array.length; i++)
            queue.add(array[i]);
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