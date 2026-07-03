all: Project1

Project1: bin/Report.class bin/Benchmark.class

bin/Report.class: src/Report.java
	javac -d bin src/Report.java

bin/Benchmark.class: src/Benchmark.java bin/InsertionSort.class bin/SelectionSort.class
	javac -d bin -cp bin src/Benchmark.java

bin/InsertionSort.class: src/InsertionSort.java bin/SortAbstract.class
	javac -d bin -cp bin src/InsertionSort.java

bin/SelectionSort.class: src/SelectionSort.java bin/SortAbstract.class
	javac -d bin -cp bin src/SelectionSort.java

bin/SortAbstract.class: src/SortAbstract.java
	javac -d bin src/SortAbstract.java



clean:
	rm -rf bin
