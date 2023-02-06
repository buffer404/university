#pragma once

#define _GNU_SOURCE
#define MAX_PATH_SIZE 1024


#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <client.h>

#include "parser.h"
#include "print_struct.h"

#include <libxml/parser.h>
#include <libxml/tree.h>

xmlDocPtr request_tree;
int size_tree;