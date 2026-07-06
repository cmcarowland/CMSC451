all: Project1

Project1: Report.class Benchmark.class

Report.class: Report.java
	javac Report.java

Benchmark.class: Benchmark.java InsertionSort.class SelectionSort.class QuickSort.class UnsortedException.class SortFactory.class
	javac Benchmark.java

InsertionSort.class: InsertionSort.java SortAbstract.class UnsortedException.class
	javac InsertionSort.java

SelectionSort.class: SelectionSort.java SortAbstract.class UnsortedException.class
	javac SelectionSort.java

QuickSort.class: QuickSort.java SortAbstract.class
	javac QuickSort.java

UnsortedException.class: UnsortedException.java
	javac UnsortedException.java

SortAbstract.class: SortAbstract.java UnsortedException.class
	javac SortAbstract.java

SortFactory.class: SortFactory.java SortAbstract.class
	javac SortFactory.java

clean:
	rm *.class
	rm *.txt
