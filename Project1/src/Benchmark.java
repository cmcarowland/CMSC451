import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Benchmark
{
    // 12 even spaced sizes starting at 100 ending at 10000
    static private int[] sizesToTest = {100,1000,1900,2800,3700,4600,5500,6400,7300,8200,9100,10000};

    static public void main(String[] args) throws UnsortedException, IOException
    {
        SortFactory selSort = () -> new SelectionSort();
        SortFactory insertSort = () -> new InsertionSort();

        for(int size : sizesToTest)
        {
            warmup(selSort, size);
            warmup(insertSort, size);
        }
        
        try (PrintWriter selOut = new PrintWriter(new FileWriter("SelectionSort.txt"));
        PrintWriter insOut = new PrintWriter(new FileWriter("InsertionSort.txt")))
        {
            for(int size : sizesToTest)
            {
                long[] selResult = new long[81];
                long[] insResult = new long[81];
                selResult[0] = size;
                insResult[0] = size;
                
                for(int i = 0; i < 40; i++)
                {
                    int[] testArray = randomArray(size);
                    long[] testResult = runTest(selSort, testArray.clone());
                    selResult[(i * 2) + 1] = testResult[0];
                    selResult[(i * 2) + 2] = testResult[1];

                    testResult = runTest(insertSort, testArray.clone());
                    insResult[(i * 2) + 1] = testResult[0];
                    insResult[(i * 2) + 2] = testResult[1];
                }

                selOut.println(toSsv(selResult));
                insOut.println(toSsv(insResult));
            }

        }

        if(args.length > 0 && args[0].equals("--auto"))
        {
            openResults("SelectionSort.txt");
            openResults("InsertionSort.txt");
        }
        /*
        int[] array = new int[]{100,99,98,97,96,95,94,93,92,91,90,89,88,87,86,85,84,83,82,81,80,79,78,77,76,75,74,73,72,71,70,69,68,67,66,65,64,63,62,61,60,59,58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        int[] array2 = array.clone();
        int[] array3 = array.clone();

        absSort.sort(array);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(array);
        System.out.println();

        absSort = new InsertionSort();
        absSort.sort(array2);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(array2);
        System.out.println();

        absSort = new QuickSort();
        absSort.sort(array3);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(array3);
        System.out.println();

        int[] shuffled = new int[100];
        for (int i = 0; i < shuffled.length; i++) {
            shuffled[i] = i;
        }
        shuffle(shuffled);
        int[] shuffled2 = shuffled.clone();
        int[] shuffled3 = shuffled.clone();

        absSort = new SelectionSort();
        absSort.sort(shuffled);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(shuffled);
        System.out.println();

        absSort = new InsertionSort();
        absSort.sort(shuffled2);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(shuffled2);
        System.out.println();

        absSort = new QuickSort();
        absSort.sort(shuffled3);
        System.out.println("Count: " + absSort.getCount() + " Time: " + absSort.getTime());
        validateSort(shuffled3);
        System.out.println();
        */
    }

    static void openResults(String fileName)
    {
        try {

            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "Report", fileName);

            pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            pb.redirectError(ProcessBuilder.Redirect.DISCARD);

            Process process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long[] runTest(SortFactory factory, int[] array)
    {
        long[] results = new long[2];
        SortAbstract absSort = factory.create();
        absSort.sort(array);
        results[0] = (long)absSort.getCount();
        results[1] = absSort.getTime();

        return results;
    }

    static String toSsv(long[] values)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++)
        {
            if (i > 0)
                sb.append(" ");

            sb.append(values[i]);
        }

        return sb.toString();
    }

    static void warmup(SortFactory factory, int size)
    {
        for (int i = 0; i < 5; i++)
            factory.create().sort(randomArray(size));
    }

    static int[] randomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100000);

        return arr;
    }

    static void shuffle(int[] array)
    {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    static public void validateSort(int[] array) throws UnsortedException
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
            {
                System.out.println("Array is not sorted");
                throw new UnsortedException("Array is not sorted");
            }
        }
        System.out.println("Array is sorted");
    }
}