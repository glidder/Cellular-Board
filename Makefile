JFLAGS = -g -d
JC = javac
BIN=./
PACK=coraline
SRC=src/
CLASSES = $(SRC)Window.java $(SRC)Framework.java $(SRC)Board.java $(SRC)Canvas.java $(SRC)Game.java 

.SUFFIXES: .java .class
all:
		$(JC) $(JFLAGS) $(BIN) $(CLASSES)

#all: classes

run: 
	java $(BIN)$(PACK).Window
#classes: $(CLASSES:$(SRC)%.java=$(BIN)%.class)

clean:
		$(RM) $(BIN)$(PACK)/*.class
