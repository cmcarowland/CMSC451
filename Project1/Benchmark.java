/*
Raymond Rowland
06 Jul 26
CMSC 451 Project 1
Benchmark.java
*/

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
        System.gc();
        
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
    }

    static void openResults(String fileName)
    {
        try {

            ProcessBuilder pb = new ProcessBuilder("java", "Report", fileName);

            pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            pb.redirectError(ProcessBuilder.Redirect.DISCARD);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long[] runTest(SortFactory factory, int[] array) throws UnsortedException
    {
        long[] results = new long[2];
        SortAbstract absSort = factory.create();
        absSort.sort(array);
        absSort.validateSort();
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
}