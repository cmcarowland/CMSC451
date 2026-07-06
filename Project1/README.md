# CMSC451 Project 1

## Requirements

Built and tested with:
- javac 21.0.11
- openjdk 21.0.11 2026-04-21 LTS
- OpenJDK Runtime Environment Microsoft-13877187 (build 21.0.11+10-LTS)

## Build

Call make from the top level folder to build all

```
$ make
javac -d bin src/Report.java
javac -d bin src/SortAbstract.java
javac -d bin -cp bin src/InsertionSort.java
javac -d bin -cp bin src/SelectionSort.java
javac -d bin -cp bin src/QuickSort.java
javac -d bin src/UnsortedException.java
javac -d bin -cp bin src/SortFactory.java
javac -d bin -cp bin src/Benchmark.java
```

Options
- Clean - removes build artifacts
- All - Builds Report and Benchmark code
- Benchmark - builds .class files required for Benchmark
- Report - builds .class files required for Report

## Usage

java Benchmark
java Report
