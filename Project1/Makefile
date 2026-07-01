all: Project1

Project1: bin/Registration.class

bin/Registration.class: bin/MyFrame.class src/Registration.java
	javac -d bin -cp bin src/Registration.java

bin/MyFrame.class: src/MyFrame.java
	javac -d bin src/MyFrame.java

clean:
	rm -rf bin
