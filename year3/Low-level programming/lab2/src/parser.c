#include "../include/parser.h"

struct request* parse_request(char* request_path, int path_size) {
	check_path(request_path, path_size);
	struct request* path_view = (struct request*)malloc(sizeof(struct request*));
	path_view->tuple_list = NULL;
	

	char cur_operation = request_path[0];
	switch (cur_operation) {
	case ADD_OPERATION:
		(*path_view).operation = ADD_OPERATION;
		break;
	case REMOVE_OPERATION:
		(*path_view).operation = REMOVE_OPERATION;
		break;
	case FIND_OPERATION:
		(*path_view).operation = FIND_OPERATION;
		break;
	case CHANGE_OPERATION:
		(*path_view).operation = CHANGE_OPERATION;
		break;
	default:
		error();
	}
	remove_char(&path_size, &request_path);

	while (path_size > 0){
		if (request_path[0] == '/') {
			remove_char(&path_size, &request_path);

			struct tuple* cur_tuple = (struct tuple*)malloc(sizeof(struct tuple*));
			memset(cur_tuple, 0, sizeof(struct tuple*));
			cur_tuple->attribute_list = NULL;
			read_tuple(&request_path, &path_size, &cur_tuple);

			cur_tuple->next_tuple = NULL;
			if (path_view->tuple_list == NULL) {
				path_view->tuple_list = cur_tuple;
			}
			else {
				struct tuple* i_tuple = path_view->tuple_list;
				while (i_tuple->next_tuple != NULL) {
					i_tuple = i_tuple->next_tuple;
				}
				(*i_tuple).next_tuple = cur_tuple;
			}
		}
		else if (request_path[0] == '[') {
			remove_char(&path_size, &request_path);

			struct attribute* cur_attr = (struct attribute*) malloc(sizeof(struct attribute));
			memset(cur_attr, 0, sizeof(struct attribute*));
			read_attribute(&request_path, &path_size, &cur_attr);

			struct tuple* i_tuple = path_view->tuple_list;
			while (i_tuple->next_tuple != NULL) {
				i_tuple = i_tuple->next_tuple;
			}
			(*i_tuple).attribute_list = cur_attr;
			
		}
		else {
			error();
		}
	}

	return path_view;

}


void read_tuple(char** request_path, int* path_size, struct tuple** cur_tuple) {

	char* tupleid = (char*) malloc(sizeof(char)*MAX_STRING);

	int iter = 0;
	while ((*request_path[0]) != '/' && *path_size != 0 && (*request_path[0]) != '[')
	{
		tupleid[iter++] = (*request_path)[0];
		remove_char(path_size, request_path);
	}
	tupleid[iter] = '\0';
	(*cur_tuple)->tupleid = tupleid;

}

void read_attribute(char** request_path, int* path_size, struct attribute** cur_attr) {

	char* left = (char*)malloc(sizeof(char) * MAX_STRING);
	char* right = (char*)malloc(sizeof(char) * MAX_STRING);
	
	int iter = 0;
	while ((*request_path[0]) != '/' && *path_size > 0 && (*request_path[0]) != '[')
	{
		if (path_size != 0 && (*request_path[0]) == ']') {
			(*cur_attr)->right = right;
			remove_char(path_size, request_path);


			if ((*request_path[0]) == '[') {
				struct attribute* next_attr = (struct attribute*)malloc(sizeof(struct attribute));
				memset(next_attr, 0, sizeof(struct attribute*));

				remove_char(path_size, request_path);
				read_attribute(request_path, path_size, &next_attr);
				(*cur_attr)->next_attribute = next_attr;
			}
			
			break;
		}
		else if ((*request_path[0]) == '>' || (*request_path[0]) == '<' || (*request_path[0]) == '=' || (*request_path[0]) == '!') {
			iter = 0;
			(*cur_attr)->left = left;
			
			if ((*request_path)[1] == '=') {
				if ((*request_path[0]) == '>')
					(*cur_attr)->condition = GoE;
				if ((*request_path[0]) == '<')
					(*cur_attr)->condition = LoE;
				remove_char(path_size, request_path);
			}
			else {
				(*cur_attr)->condition = (*request_path[0]);
			}
		}
		else if ((*request_path[0]) == '&' || (*request_path[0]) == '|') {
			(*cur_attr)->right = right;
			(*cur_attr)->combined_condition = (*request_path[0]);
			struct attribute* next_attr = (struct attribute*)malloc(sizeof(struct attribute));
			memset(next_attr, 0, sizeof(struct attribute*));
			remove_char(path_size, request_path);
			read_attribute(request_path, path_size, &next_attr);
			(*cur_attr)->combined_attribute = next_attr;
		}
		else {
			if ((*cur_attr)->condition == NULL) {
				left[iter++] = (*request_path)[0];
			}
			else {
				right[iter++] = (*request_path)[0];
			}
		}
		remove_char(path_size, request_path);
	}

}


void error() {
	printf("The request is incorrectly written\n");
	exit(EXIT_FAILURE);
}

void remove_char(int* size, char** request_path) {
	(*size)--;
	(*request_path)++;
}

void check_path(char* request_path, int path_size) {
	int bracket = 0;
	for (size_t i = 0; i < path_size; i++){
		if (request_path[i] == '[')
			bracket++;
		if (request_path[i] == ']')
			bracket--;
	}
	if (bracket != 0) {
		error();
	}
}