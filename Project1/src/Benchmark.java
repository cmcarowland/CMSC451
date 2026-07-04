import java.util.Random;

class Benchmark
{
    static public void main(String[] args) throws UnsortedException
    {
        SortAbstract absSort = new SelectionSort();
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