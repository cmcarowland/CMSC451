public class Heap {
    private int[] heap;
    private int size;

    public Heap(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
    }

    public void insert(int value) {
        int index = size;
        heap[size++] = value;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index] <= heap[parentIndex]) {
                break;
            }

            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
        }
    }

    public int remove() {
        int removedValue = heap[0];
        int last = heap[--size];
        if (size > 0) {
            heap[0] = last;
            heapify(0, size);
        }

        return removedValue;
    }

    public int size() {
        return size;
    }

    public boolean isSorted() {
        for (int i = 0; i < size - 1; i++) {
            if (heap[i] < heap[i + 1]) {
                return false;
            }
        }

        return true;
    }

    private void heapify(int i, int n) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && heap[l] > heap[largest])
            largest = l;

        if (r < n && heap[r] > heap[largest])
            largest = r;

        if (largest != i) {
            int swap = heap[i];
            heap[i] = heap[largest];
            heap[largest] = swap;

            heapify(largest, n);
        }
    }
}
