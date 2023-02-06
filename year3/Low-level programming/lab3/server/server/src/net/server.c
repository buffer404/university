#include "../../include/server.h"


int start_server(int port) {
    struct sockaddr_in adr = { 0 };
    int server = Socket(AF_INET, SOCK_STREAM, 0);
    adr.sin_family = AF_INET;
    adr.sin_port = htons(port);
    Bind(server, (struct sockaddr*)&adr, sizeof adr);
    return server;
}

int handler_request(int server, char buf[]) {
    struct sockaddr_in adr = { 0 };
    Listen(server, 5);
    socklen_t adrlen = sizeof adr;
    int fd = Accept(server, (struct sockaddr*)&adr, &adrlen);
    ssize_t nread;
    nread = read(fd, buf, 1024);
    if (nread == -1) {
        perror("read failed");
        exit(EXIT_FAILURE);
    }
    if (nread == 0) {
        printf("END OF FILE occured\n");
    }
    return fd;
}

void send_response(char* msg, int fd) {
    write(fd, msg, 128);
}

void finish_server(int server) {
    close(server);
}