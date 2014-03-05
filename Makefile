JFLAGS = -g -d
JC = javac
BIN=./
SRC=src/
CLASSES = $(SRC)Life.java $(SRC)LifeGUI.java #$(SRC)Board.java $(SRC)Cell.java $(SRC)Game.java 

.SUFFIXES: .java .class
all:
		$(JC) $(JFLAGS) $(BIN) $(CLASSES)

#all: classes

run: 
	java Game $(BIN)Game
#classes: $(CLASSES:$(SRC)%.java=$(BIN)%.class)

clean:
		$(RM) $(BIN)*.class
