#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "erproc.h"

int start_server(int port);

int handler_request(int server, char buf[]);

void send_response(char* msg, int fd);

void finish_server(int server);