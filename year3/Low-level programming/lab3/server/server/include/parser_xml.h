#pragma once

#include <libxml/tree.h>
#include <libxml/parser.h>
#include "interface/crud_interface.h"
#include "server.h"



void execute_request(char xml_request[], FILE* file, int fd);