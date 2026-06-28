/*
Provide a Java class named SortedPriorityQueue that implements 
a priority queue using a Java array of type int. 

The constructor of the class should be passed the size of the queue. 

Each time the add method is called, the value passed to it should be 
inserted into the array to ensure that the array remains in sorted order. 

If the array is full when add is called, a RuntimeException should be thrown. 

If the array is empty when remove is called, 
a RuntimeException should also be thrown Make the implementation 
as efficient as possible. 
*/
class SortedPriorityQueue
{
    private int capacity;
    private Heap heap;

    public SortedPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new Heap(capacity);
    }

    public void add(int value) {
        // Implementation for inserting a value into the sorted priority queue
        if (heap.size() >= capacity) {
            throw new RuntimeException("Priority queue is full");
        }
        heap.insert(value);
    }

    public int remove() {
        // Implementation for removing and returning the highest priority value
        if (heap.size() == 0) {
            throw new RuntimeException("Priority queue is empty");
        }

        return heap.remove();
    }

    public boolean isSorted() {
        return heap.isSorted();
    }

    public int size() {
        return heap.size();
    }
}