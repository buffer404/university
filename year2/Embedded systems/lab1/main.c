#include <stdio.h>
#include<inttypes.h>
#include "var.h"

int main() {
<<<<<<< HEAD
    f = fopen("1.exe", "rb");
=======
    f = fopen("test.exe", "rb");
>>>>>>> 0c1c239b235a2d06e4480776d2bfb4f264e97281

    fseek( f, 0x3C, SEEK_SET);
    fread(&file_header, sizeof(uint32_t), 1, f);

    // Количество секций
    fseek( f, file_header+0x6, SEEK_SET);
    fread(&number_of_sections, sizeof(uint16_t), 1, f);

    // Адрес точки входа
    fseek( f, file_header+0x28, SEEK_SET);
    fread(&addr_of_entry_point, sizeof(uint32_t), 1, f);

    write_section();
    fclose(f);
    return 0;
}

int write_section() {
    section section1;
    section_information = fopen("section.txt", "w");
    fprintf(section_information, "%s", "Address of entry point: ");
    fprintf(section_information, "%" PRIx32 "\n", addr_of_entry_point);
    for (int i = 0; i < number_of_sections; ++i) {
        fseek( f, (file_header+0x108)+(i*0x28), SEEK_SET);
        fread(&section1, sizeof(section), 1, f);
        fprintf(section_information, "\nName: %s", (char*)(&(section1.name)));
        fprintf(section_information, "\nVirtual Size: %" PRIx32, section1.virtual_size);
        fprintf(section_information, "\nVirtual Address: %" PRIx32, section1.virtual_address);
        fprintf(section_information, "\nRaw Address: %" PRIx32, section1.raw_address);
        fprintf(section_information, "\nRaw Size: %" PRIx32, section1.raw_size);
        fprintf(section_information, "\nReloc Address: %" PRIx32, section1.reloc_address);
        fprintf(section_information, "\nLinenumbers: %" PRIx32, section1.linenumbers);
        fprintf(section_information, "\nRelocation Number: %" PRIx16, section1.relocations_numbers);
        fprintf(section_information, "\nLinenumbers Numbers: %" PRIx16, section1.linenumbers_numbers);
        fprintf(section_information, "\nCharacteristics: %" PRIx32 "\n", section1.characteristics);
        if(section1.characteristics & 0x00000020){
            write_text(section1.raw_size, section1.raw_address);
        }
    }
    fclose(section_information);
}

void write_text(uint32_t raw_size, uint32_t raw_address) {
    text = fopen("text.txt", "w");
    fseek(f,  raw_address, SEEK_SET);
    for (uint32_t i = 0x0; i < raw_size; ++i) {
        uint8_t cur_byte;
        fread(&cur_byte, sizeof(uint8_t), 1, f);
        fputc(cur_byte, text);
    }
    fclose(text);
<<<<<<< HEAD
=======

>>>>>>> 0c1c239b235a2d06e4480776d2bfb4f264e97281
}