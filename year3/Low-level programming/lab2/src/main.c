#include "../include/main.h"

int main() {

    char name[MAX_PATH_SIZE];
    puts("Enter path:");

    fgets(name, MAX_PATH_SIZE, stdin);

    size_t len = strlen(name);

    struct request* path_view = parse_request(name, len-1);

    print_struct(path_view);

	return 0;
}

// -/6654/*[weight<100.9][sex="male"&age>22]
// -/6654/*[weight<=100.9][sex="male"&age>22|name!"leo"]