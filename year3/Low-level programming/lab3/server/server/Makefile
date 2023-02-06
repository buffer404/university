CC=gcc
CFLAGS= -I/usr/include/libxml2 -Iinclude -Wextra -DDEBUG -g -o0 `xml2-config --cflags`
LIBS= `xml2-config --libs`

BUILDDIR=build
SRCDIR=src
TARGETDIR=.

SOURCES=$(shell find ./$(SRCDIR) -name "*.c")
EXECUTABLE=main

all: build run

run:
	./$(EXECUTABLE) simple.txt 37471

build: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(SOURCES)
	$(CC) $(CFLAGS) $(SOURCES) `xml2-config --libs` -o $@ `xml2-config --cflags`


clean:
	rm -rf $(BUILDDIR) $(EXECUTABLE)