#include "../../include/client.h"

void sendRequest(int port, int str_len, char request[]) {
    int fd = Socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in adr = { 0 };
    adr.sin_family = AF_INET;
    adr.sin_port = htons(port);
    Inet_pton(AF_INET, "127.0.0.1", &adr.sin_addr);
    Connect(fd, (struct sockaddr*)&adr, sizeof adr);
    write(fd, request, str_len-1);
    char buf[1024];
    ssize_t nread;
    nread = read(fd, buf, 1024);
    if (nread == -1) {
        perror("read failed");
        exit(EXIT_FAILURE);
    }
    if (nread == 0) {
        printf("EOF occured\n");
    }
    printf("%s\n\n", buf);
    close(fd);
}

// +/2/5[name=leo]
// -/3