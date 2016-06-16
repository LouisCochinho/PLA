SRC = $(wildcard Automates/*.ml)
PROG = $(patsubst Automates/%.ml,%,$(SRC))

all: $(PROG) clean

%: Automates/%.ml
	cat lib/part1.ml $< lib/part3.ml > temp/$@.ml
	ocamlc temp/$@.ml -o temp/$@
	./temp/$@
	mv test.xml xml/$@.xml

clean:
	rm temp/*
