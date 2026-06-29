# CMSC451

## Requirements

Built and tested with:
- javac 25.0.2
- openjdk 25.0.2 2026-01-20 LTS

## Build

Call make from the top level folder to build all

```
$ make
javac -d bin src/Homework3.java src/SortedPriorityQueue.java src/Heap.java
javac -d bin src/TestRunner.java src/SortedPriorityQueue.java src/Heap.java
```

Options
- Clean - removes build artifacts
- All - Builds all TestRunner and Homework code
- Homework3 - Builds files required for Homework3 to run
- TestRunner - Builds files required for Homework3 and TestRunner to run

## Usage

Usage: Please select SPQ for Sorted Priority Queue or PQ for Priority Queue
java -cp bin Homework SPQ // Runs against the SortedPriorityQueue
java -cp bin Homework PQ // Runs against the PriorityQueue
