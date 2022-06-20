#include<inttypes.h>
#include <stdio.h>

typedef struct  __attribute__((packed)){
    uint64_t name;
    uint32_t virtual_size;
    uint32_t virtual_address;
    uint32_t raw_size;
    uint32_t raw_address;
    uint32_t reloc_address;
    uint32_t linenumbers;
    uint16_t relocations_numbers;
    uint16_t linenumbers_numbers;
    uint32_t characteristics;
} section;

FILE *f;
FILE *text;
FILE *section_information;
uint32_t addr_of_entry_point;
uint16_t number_of_sections;
uint32_t file_header;



void write_text(uint32_t raw_size, uint32_t raw_address);
int write_section();