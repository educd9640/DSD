JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		PoligonoIList.java\
		PruebaPoligonoIList.java\
		SortMagnitud.java\

default:classes


classes:$(CLASSES:.java=.class)

clean:
	@rm -f *.class

run:
	@java PruebaPoligonoIList
