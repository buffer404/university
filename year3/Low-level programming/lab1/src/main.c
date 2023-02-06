#include "../include/interface/basic_crud.h"
#include "../include/data/test_data.h"

int main(int argc, char** argv) {
    FILE *file;
    open_file_anyway(&file, "simple.txt");

    size_t* pattern_size = malloc(sizeof(size_t));
    size_t* tuple_count = malloc(sizeof(size_t));
    char **pattern;
    uint32_t *types;
    size_t *sizes;

    get_test_header(&pattern, &types, pattern_size, &sizes, tuple_count);

    init_empty_file(file, pattern, types, *pattern_size, sizes);

    get_test_data(file, *tuple_count, *pattern_size, types);

    print_tree_header_from_file(file);

    print_tuple_array_from_file(file);

    free(pattern_size);
    free(tuple_count);
    free(pattern);
    free(types);
    free(sizes);

    return 0;
}


