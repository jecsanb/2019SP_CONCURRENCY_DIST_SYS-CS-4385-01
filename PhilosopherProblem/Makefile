# template from:https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Main.java 

default: classes

classes: $(CLASSES:.java=.class)

run:
	java Main
clean:
	$(RM) *.class
