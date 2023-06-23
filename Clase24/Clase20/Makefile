JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  WebServer.java\
		  SearchCadena.java\
		  Multihilo.java\

default:classes


classes:$(CLASSES:.java=.class)

clean:
	@rm -f *.class

run:
	@java Multihilo.java
