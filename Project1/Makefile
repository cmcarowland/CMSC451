all: Project1

Project1: bin/Project.class

bin/Project.class: bin/Report.class src/Project.java
	javac -d bin -cp bin src/Project.java

bin/Report.class: src/Report.java
	javac -d bin src/Report.java

clean:
	rm -rf bin
