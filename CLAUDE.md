# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

CMSC451 (Design and Analysis of Algorithms) coursework in Java. Requires JDK 25 (`javac` 25). No third-party dependencies or build framework beyond `make` + `javac`.

## Build & Run

```
make            # builds Homework3 + TestRunner into bin/
make Homework3  # builds only Homework3 and its deps
make TestRunner # builds only TestRunner and its deps
make clean      # rm -rf bin
```

Run the benchmark (note: the class is `Homework3`, not `Homework` as the README shows):

```
java -cp bin Homework3 SPQ   # benchmark SortedPriorityQueue
java -cp bin Homework3 PQ    # benchmark java.util.PriorityQueue (baseline)
```

Run the unit tests (no runner script — invoke `main` directly):

```
java -cp bin TestRunner
```

There is no test framework; `TestRunner` is a hand-rolled harness with a `main` method and a static `assertEquals`. Tests print PASS/FAIL with ANSI colors and a pass/fail tally. To add a test, write a `testXxx()` method and call it from `main`.

## Architecture (`src/`)

The name is misleading: `SortedPriorityQueue` does **not** keep a sorted array (despite the docstring in the file describing that contract). It is a thin wrapper that delegates to `Heap`, an array-backed **max-heap**. `remove()` returns the largest element. Capacity is fixed at construction — `add` past capacity and `remove` on empty both throw `RuntimeException`.

- `Heap.java` — the actual data structure: `int[]` max-heap with sift-up `insert`, sift-down `heapify` on `remove`.
- `SortedPriorityQueue.java` — capacity-guarded facade over `Heap`.
- `Homework3.java` — CLI benchmark harness. Compares `SortedPriorityQueue` vs `java.util.PriorityQueue` across sizes {100, 1000, 10000, 100000}, timing a full add-all-then-remove-all "sort". Includes a JIT warm-up loop whose results are discarded and uses more trials for small `n`.
- `TestRunner.java` — correctness tests for `SortedPriorityQueue`.

## Project1/ (separate, in progress)

An untracked, standalone Swing assignment — not part of the `make` build and unrelated to `src/`. `Registration.java` is a `JFrame` scaffold; `TODO.md` specifies the target `Report.java` (JFileChooser input, results in a JTable with five columns). `QuickSort.txt` holds sample data-set measurements. Compile these separately with `javac`.
