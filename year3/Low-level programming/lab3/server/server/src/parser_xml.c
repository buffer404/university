#include <parser_xml.h>

void execute_request(char xml_request[], FILE* file, int fd) {
	xmlDocPtr request_tree = xmlReadMemory(xml_request, 1024, 0, NULL, XML_PARSE_RECOVER);
	xmlNodePtr lastNode = request_tree->last;
	while (lastNode->last != NULL) {
		lastNode = lastNode->last;
	}

	char* str = "Request error, this tuple does not exist\n";
	if (xmlStrEqual(request_tree->last->name, xmlCharStrdup("add"))){
		uint64_t fields[2];
		fields[0] = (uint64_t) xmlGetProp(lastNode, xmlCharStrdup("name"));
		fields[1] = (uint64_t) atoi(xmlGetProp(lastNode, xmlCharStrdup("age")));
		if (add_tuple(file, fields, atoi(xmlGetProp(lastNode->parent, xmlCharStrdup("id")))) == 0) {
			str = "Tuple successfully added!";
		}
	}
	else if (xmlStrEqual(request_tree->last->name, xmlCharStrdup("remove"))){
		if (remove_tuple(file, atoi(xmlGetProp(lastNode, xmlCharStrdup("id"))), 0) == 0) {
			str = "The element has been successfully deleted!";
		}
	}
	else if (xmlStrEqual(request_tree->last->name, xmlCharStrdup("find"))) {
		char test [1024] = "";
		if (xmlStrEqual(xmlGetProp(lastNode, xmlCharStrdup("id")), xmlCharStrdup("*"))) {
			find_all_by_parent(file, test, atoi(xmlGetProp(lastNode->parent, xmlCharStrdup("id"))));
		} else{
			uint64_t fields[2];
			fields[1] = atoi(xmlGetProp(lastNode, xmlCharStrdup("age")));
			find_all_by_fields(file, test, fields);
		}
		str = test;
	}
	else{
		uint64_t param = atoi(xmlGetProp(lastNode, xmlCharStrdup("age")));
		if (update_tuple(file, 1, &param, atoi(xmlGetProp(lastNode, xmlCharStrdup("id")))) == 0) {
			str = "Tuple has been successfully updated!";
		}
	}
	
	send_response(str, fd);
}