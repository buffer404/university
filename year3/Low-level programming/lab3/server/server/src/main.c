#include <main.h>

// simple.txt 37471
int main(int argc, char** argv) {
    FILE* file;
    open_file_anyway(&file, argv[1]);

    size_t* pattern_size = malloc(sizeof(size_t));
    size_t* tuple_count = malloc(sizeof(size_t));
    char** pattern;
    uint32_t* types;
    size_t* sizes;

    get_test_header(&pattern, &types, pattern_size, &sizes, tuple_count);
    init_empty_file(file, pattern, types, *pattern_size, sizes);
    get_test_data(file, *tuple_count, *pattern_size, types);


    print_tree_header_from_file(file);
    printf("%s\n", "Before request");
    print_tuple_array_from_file(file);



    int server = start_server(atoi(argv[2]));

    while (1) {
        char request_xml[1024] = { 0 };

        int fd = handler_request(server, request_xml);

        xmlDocPtr request_tree = xmlReadMemory(request_xml, 1024, 0, NULL, XML_PARSE_RECOVER);

        execute_request(request_xml, file, fd);
        printf("%s\n", "After request");
        print_tree_header_from_file(file);
        print_tuple_array_from_file(file);
    }

    return(0);
}