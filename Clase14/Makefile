JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  WebServer.java\
		  SearchCadena.java\

default:classes


classes:$(CLASSES:.java=.class)

clean:
	@rm -f *.class

run:
	@java WebServer.java
