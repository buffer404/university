#pragma once

#include <stdio.h>
#include <stdlib.h>
#include "request.h"

void print_struct(struct request* path_view);

void print_operation(char op);

void print_tupleid(char* tupleid);

void print_attribute(struct attribute* cur_attr, int main_flag);

void print_value(char* value, int idx);

void print_combined_condition(char condition);