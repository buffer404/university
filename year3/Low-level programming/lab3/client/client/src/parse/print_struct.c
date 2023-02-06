#include "../../include/print_struct.h"

void print_struct(struct request* path_view) {
	int k = 0;
	int level = 0;
	
	print_operation((*path_view).operation);
	printf("%c", '\n');

	struct tuple* cur_tuple = path_view->tuple_list;

	while (k == 0)
	{
		level++;
		if (cur_tuple->next_tuple == NULL) {
			k = 1;
		}

		printf("Deep level: %d\n", level);

		print_tupleid((*cur_tuple).tupleid);
		
		if (cur_tuple->attribute_list != NULL){
			print_attribute(cur_tuple->attribute_list, 1);
			if (cur_tuple->attribute_list->next_attribute !=NULL){
				printf("Next condition for tuple\n");
				print_attribute(cur_tuple->attribute_list->next_attribute, 1);
			}
		}
		
		cur_tuple = cur_tuple->next_tuple;
		printf("%c", '\n');
	}

}

void print_attribute(struct attribute* cur_attr, int main_flag) {
	printf("Attribute:\n");
	printf("Opperand 1, Tuple.%s\n", cur_attr->left);
	print_combined_condition((*cur_attr).condition);
	print_value(cur_attr->right, 2);
	struct attribute* first_attr = cur_attr;

	if (main_flag == 1) {
		while (cur_attr->combined_condition != NULL){
				printf("Continuation of the attribute request, condition: %c\n", (*cur_attr).combined_condition);

				print_attribute(cur_attr->combined_attribute, 0);
				cur_attr = cur_attr->combined_attribute;
			}
	}
	

}

void print_combined_condition(char condition) {
	printf("Continuation of the attribute request, condition: ");
	switch (condition){
	case '=':
		printf("= : equally\n");
		break;
	case '>':
		printf("> : more\n");
		break;
	case '<':
		printf("< : less\n");
		break;
	case '1':
		printf(">= : greater than or equal to\n");
		break;
	case '2':
		printf(">= : greater than or equal to\n");
		break;
	case '!':
		printf("! : not\n");
		break;

	}
}

void print_value(char* value, int idx) {

	if (atof(value) != 0) {
		printf("Opperand %d, Type: float, value: %s\n",idx, value);
	}
	else if (atof(value) != 0) {
		printf("Opperand %d, Type: integer, value: %s\n", idx, value);
	}
	else {
		printf("Opperand %d, Type: string, value: %s\n", idx, value);
	}

	

}

void print_operation(char op) {
	switch (op) {
	case '-':
		printf("%s\n", "Remove from Selection");
		break;
	case '+':
		printf("%s\n", "Add to Selection");
		break;
	case '?':
		printf("%s\n", "Find in the selection");
		break;
	case '=':
		printf("%s\n", "Edit in the selection");
		break;
	}
}

void print_tupleid(char* tupleid) {
	if (tupleid[0] == '*')
	{
		printf("* - For all tuples\n");
	}
	else {
		printf("Where id = %s\n", tupleid);
	}
}