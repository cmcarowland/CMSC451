# CMSC451 Project 1

## Requirements

Built and tested with:
- javac 21.0.11
- openjdk 21.0.11 2026-04-21 LTS
- OpenJDK Runtime Environment Microsoft-13877187 (build 21.0.11+10-LTS)

| Resource | Stats |
| -- | -- |
| CPUs | 2 (1 core, 2 threads — hyperthreaded, not physical cores) |
|CPU model | AMD EPYC 7763, virtualized under Microsoft Hyper-V (Azure) |
| RAM |	7.8Gi total, 4.9Gi available |
| Swap | 0B (none configured) | 
| Java | OpenJDK 21.0.11 |

## Build

Call make from the top level folder to build all

```
$ make
javac Report.java
javac UnsortedException.java
javac SortAbstract.java
javac InsertionSort.java
javac SelectionSort.java
javac QuickSort.java
javac SortFactory.java
javac Benchmark.java
```

Options
- Clean - removes build artifacts
- All - Builds Report and Benchmark code
- Benchmark - builds .class files required for Benchmark
- Report - builds .class files required for Report

## Usage

`java Benchmark`

Benchmark will create one file for each algorithm. InsertionSort.txt and SelectionSort.txt will be generated when the benchmark program is run.

`java Report`

Pass --auto to Benchmark to open reports for each algorithm automatically.

`java Benchmark --auto`

Pass a filename to Report to open it straight into the table

`java Report SelectionSort.txt`
