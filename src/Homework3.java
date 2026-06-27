import java.util.Arrays;

class Homework3
{
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println("Before sorting: " + java.util.Arrays.toString(arr));
        sort(arr);
        System.out.println("After sorting: " + java.util.Arrays.toString(arr));
    }

    static void sort(int[] array) 
    { 
        SortedPriorityQueue queue = new SortedPriorityQueue(3); 
        for (int i = 0; i < array.length; i++) 
            queue.add(array[i]); 
        for (int i = 0; i < array.length; i++) 
            array[i] = queue.remove(); 
    }
} 