
class Benchmark 
{
    static public void main(String[] args)
    {
        System.out.println("Hello From Benchmark");
        SortAbstract absSort = new SelectionSort();
        absSort.sort(new int[]{1,2,3});

        absSort = new InsertionSort();
        absSort.sort(new int[]{4,5,6});
    }
}