

public abstract class SortAbstract
{
    abstract public void sort(int[] array);
    abstract protected void startSort();
    abstract protected void endSort();
    abstract protected void addToCount();
    abstract public int getCount();
    abstract public long getTime();
}