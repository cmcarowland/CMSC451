/*
Raymond Rowland
06 Jul 26
CMSC 451 Project 1
InsertionSort.java
Found at: https://www.geeksforgeeks.org/dsa/insertion-sort-algorithm/
*/

public class InsertionSort extends SortAbstract
{
    @Override 
    public void sort(int[] array)
    {
        startSort();
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            /* Move elements of array[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0) { 
                addToCount();
                if(array[j] <= key) 
                    break;
                
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = key;
        }
        endSort();
    }
}