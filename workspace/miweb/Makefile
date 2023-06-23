JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		PruebaRectangulo.java\
		Rectangulo.java\
		Coordenada.java\
		PoligonoIrregular.java\
		PruebaPoli.java\
		PruebaPoliSet.java\

default:classes


classes:$(CLASSES:.java=.class)

clean:
	@rm -f *.class

run:
	@java PruebaRectangulo
	@java PruebaPoli
	@java PruebaPoliSet
