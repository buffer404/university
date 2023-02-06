#pragma once
#include "request.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libxml/tree.h>

struct request* parse_request(char* request, int path_size, xmlDocPtr request_tree);
void read_tuple(char** request_path, int* path_size, struct tuple** cur_tuple);
void read_attribute(char** request_path, int* path_size, struct attribute** cur_tuple);
void error();

void remove_char(int* size, char** request_path);
void check_path(char* request_path, int path_size);