#PROYECTO 2
#EDUARDO CALLEJAS DIAZ	4CM12 
#
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		PoligonoReg.java\
		SimpleGui2.java\
		SortArea.java\

default:classes


classes:$(CLASSES:.java=.class)

clean:
	@rm -f *.class

run:
	@java SimpleGui2
