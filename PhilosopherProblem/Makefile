# template from:https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html
JFLAGS = -g
n? = 5
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Main.java \
        MainV2.java \

default: classes

classes: $(CLASSES:.java=.class)

run:
	java Main
run2:
	java MainV2
clean:
	$(RM) *.class
