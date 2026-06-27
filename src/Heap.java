import java.util.ArrayList;

public class Heap {
    private ArrayList<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<Integer>();
    }

    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) <= heap.get(parentIndex)) {
                break;
            }
            int temp = heap.get(index);
            heap.set(index, heap.get(parentIndex));
            heap.set(parentIndex, temp);
            index = parentIndex;
        }
    }

    public int remove() {
        if (heap.size() == 0) {
            throw new RuntimeException("Queue is empty");
        }

        int removedValue = heap.get(0);
        heap.remove(0);
        heapify(0, heap.size());
        return removedValue;
    }

    public int size() {
        return heap.size();
    }

    private void heapify(int i, int n) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && heap.get(l) > heap.get(largest))
            largest = l;

        if (r < n && heap.get(r) > heap.get(largest))
            largest = r;

        if (largest != i) {
            int swap = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, swap);

            heapify(n, largest);
        }
    }
}
