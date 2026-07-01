public class TestRunner {
    static int passed = 0, failed = 0;
    static final String GREEN = "[32m";
    static final String RED = "[91m";
    static final String RESET = "[0m";

    static <T> void assertEquals(T expected, T actual, String testName) {
        if (expected.equals(actual)) {
            System.out.println("[" + GREEN + "PASSED" + RESET + "] : " + testName);
            passed++;
        } else {
            System.out.println("[" + RED + "FAILED" + RESET + "] : " + testName + " - Expected: " + expected + ", Actual: " + actual);
            failed++;
        }
    }

    static void testBasicFunctionality() {
        SortedPriorityQueue queue = new SortedPriorityQueue(5);
        assertEquals(0, queue.size(), "Test 1.1: Initial size");
        queue.add(10);
        assertEquals(1, queue.size(), "Test 1.2: Size after adding one element");
        queue.add(20);
        assertEquals(2, queue.size(), "Test 1.3: Size after adding two elements");
        queue.add(15);
        assertEquals(3, queue.size(), "Test 1.4: Size after adding three elements");
        assertEquals(20, queue.remove(), "Test 1.5: Remove highest priority");
        assertEquals(15, queue.remove(), "Test 1.6: Remove next highest priority");
        assertEquals(10, queue.remove(), "Test 1.7: Remove last element");
        assertEquals(0, queue.size(), "Test 1.8: Size after removing all elements");
    }   
        
    static void testFullQueueException() {
        SortedPriorityQueue queue = new SortedPriorityQueue(5);
        try {
            queue.add(10);
            queue.add(20);
            queue.add(15);
            queue.add(30);
            queue.add(40);
            queue.add(50);
            queue.add(60);
            assertEquals(true, false, "Test 2 - Expected RuntimeException for full queue");
        } catch (RuntimeException e) {
            assertEquals(true, e instanceof RuntimeException, "Test 2 - Caught expected RuntimeException for full queue");
        }
    }

    static void testEmptyQueueException() throws Exception {
        SortedPriorityQueue queue = new SortedPriorityQueue(5);
        try {
            queue.remove();
            assertEquals(true, false, "Test 3 - Expected RuntimeException for empty queue");
        } catch (Exception e) {
            assertEquals(true, e instanceof RuntimeException, "Test 3 - Caught expected RuntimeException for empty queue");
        }
    }

    public static void main(String[] args) throws Exception {
        testBasicFunctionality();
        testFullQueueException();
        testEmptyQueueException();

        System.out.println("\nTotal tests " + GREEN + "[PASSED]" + RESET + ": " + passed);
        System.out.println("Total tests " + RED + "[FAILED]" + RESET + ": " + failed);
    }
}
