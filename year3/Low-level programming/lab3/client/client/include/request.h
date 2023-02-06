#pragma once

#define MAX_STRING 64
#define LoE '2'
#define GoE '1'


enum operation {
	ADD_OPERATION = '+',
	REMOVE_OPERATION = '-',
	FIND_OPERATION = '?',
	CHANGE_OPERATION = '='
};

enum attribute_condition {
	MORE = '>',
	LESS = '<',
	EQUAL = '=',
	NEG = '!',
	MOREE = '1',
	LESSE = '2',
	TRUE = '$'
};

enum bool_condition {
	AND = '&',
	OR = '|',
	NOT = '!'
};

struct attribute {
	char* left;
	char* right;
	enum attribute_condition condition;
	enum bool_condition combined_condition;
	struct attribute* combined_attribute;
	struct attribute* next_attribute;
};

struct tuple {
	char* tupleid;
	struct attribute* attribute_list;
	struct tuple* next_tuple;
};

struct request {
	char operation;
	struct tuple* tuple_list;
};

enum condition {
	START,
	READ_OPERATION,
	READ_TUPLE,
	READ_ATTRIBUTE, 
	ERROR, 
	FINISH
};