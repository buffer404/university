CC=gcc
CFLAGS= -Wall -Isrc/ -Wextra -DDEBUG -g -o0

BUILDDIR=build
SRCDIR=src
TARGETDIR=.

SOURCES=$(shell find ./$(SRCDIR) -name "*.c")
EXECUTABLE=main

all: build run

run:
	./$(EXECUTABLE)

build: $(SOURCES) $(EXECUTABLE)


$(EXECUTABLE): $(SOURCES)
	$(CC) $(CFLAGS) $(SOURCES) -o $@


clean:
	rm -rf $(BUILDDIR) $(EXECUTABLE)
