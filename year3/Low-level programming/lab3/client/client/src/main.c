#include <main.h>

int main(int argc, char** argv){

    char str[MAX_PATH_SIZE] = "";

    //char test[1024] = "";
    //xmlDocPtr request_tree = xmlReadMemory(test, 42, 0, NULL, XML_PARSE_RECOVER);
    //xmlSaveFormatFileEnc("-", request_tree, NULL, 1);

    while (1) {
        char str[MAX_PATH_SIZE] = "";

        puts("Enter path:");
        fgets(str, MAX_PATH_SIZE, stdin);

        size_t len = strlen(str);


        request_tree = xmlNewDoc(BAD_CAST "1.0");

        struct request* path_view = parse_request(str, len - 1, request_tree);
    
        xmlChar* str_tree = (xmlChar*)malloc(sizeof(xmlChar) * 1024);

        xmlDocDumpMemory(request_tree, &str_tree, &size_tree);

        sendRequest(atoi(argv[1]), size_tree, str_tree);
        xmlFreeDoc(request_tree);
        free(str_tree);
    }

    return(0);
}

// +/0/1[age=777][name=max]
// -/1
// =/1[age=12345]
// ?/2/*
// ?/*[name=leo][age>99]
