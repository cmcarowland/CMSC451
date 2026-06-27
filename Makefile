Homework3: bin/Homework3.class

bin/Homework3.class: src/Homework3.java src/SortedPriorityQueue.java src/Heap.java
	javac -d bin src/Homework3.java src/SortedPriorityQueue.java src/Heap.java

clean:
	rm -rf bin