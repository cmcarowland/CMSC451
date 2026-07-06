/*
Raymond Rowland
06 Jul 26
CMSC 451 Project 1
SortAbstract.java
*/

public abstract class SortAbstract
{
    protected int count;
    protected long startTime;
    protected long elapsedTime;
    protected int[] array;

    abstract public void sort(int[] array);
    
    protected void startSort()
    {
        count = 0;
        startTime = System.nanoTime();
    }

    protected void endSort()
    {
        elapsedTime = System.nanoTime() - startTime;
    }

    protected void addToCount()
    {
        count++;
    }
    
    public int getCount()
    {
        return count;
    }

    public long getTime()
    {
        return elapsedTime;
    }

    public void validateSort() throws UnsortedException
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
            {
                throw new UnsortedException("Array is not sorted");
            }
        }
    }
}