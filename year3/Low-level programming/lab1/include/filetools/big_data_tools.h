#ifndef BIG_DATA_TOOLS_H
#define BIG_DATA_TOOLS_H
#include <stdlib.h>

#include "../interface/configuration.h"
#include "../interface/signatures.h"
#include "../generator/empty_generator.h"
#include "basic_file_manager.h"

/**
 * Получение реального размера массива линков
 * @param pattern_size размер массива шаблона
 * @return размер внутренних данных кортежа
 */
size_t get_real_id_array_size(uint64_t pattern_size, uint64_t cur_id);

/**
 * Получение реального размера кортежа
 * @param pattern_size размер массива шаблона
 * @return размер внутренних данных кортежа
 */
size_t get_real_tuple_size(uint64_t pattern_size);

size_t get_id_array_size(uint64_t pattern_size, uint64_t cur_id);

/**
 * Прочитать обычный кортеж
 * @param tuple контейнер для кортежа
 * @param file файл
 * @param tree_header заголовок
 * @return статус чтения
 */


/**
 * Прочитать строчный кортеж
 * @param tuple контейнер для кортежа
 * @param file файл
 * @param tree_header заголовок дерева
 * @return статус чтения
 */
enum file_read_status read_string_tuple(FILE *file, struct tuple **tuple, uint64_t pattern_size);

/**
 * Получить строку
 * @param string контейнер для строки
 * @param pattern_size размер кортежа, в которм хранится строка
 * @param offset смещение, где находится начало строки
 * @return статус чтения
 */
enum file_read_status read_string_from_tuple(FILE *file, char **string, uint64_t pattern_size, uint64_t offset);

/**
 * Записать заголовок файла
 * @param file файл
 * @param header заголовок файла
 * @param pattern_size размер массива шаблона
 * @return статус записи
 */
enum file_write_status write_tree_header(FILE *file, struct tree_header *header);

/**
 * Создать файл с пустой структурой
 * @param file файл
 * @param pattern массив строк для шаблона
 * @param types типы полей шаблона
 * @param pattern_size размер массива шаблона
 * @param key_sizes размеры строк шаблона
 * @return статус записи
 */
enum file_write_status init_empty_file(FILE *file, char **pattern, uint32_t *types, size_t pattern_size, size_t *key_sizes);

/**
 * Открытие файла или его создание в случае его отсутствия
 * @param file контейнер для файлового потока
 * @param filename имя файла
 * @return статус открытия
 */
enum file_open_status open_file_anyway(FILE **file, char *filename);

/**
 * Запись кортежа в файл
 * @param file файл
 * @param tuple кортеж
 * @return статус записи
 */
enum file_write_status write_tuple(FILE *file, struct tuple *tuple, size_t tuple_size);

enum file_read_status read_basic_tuple(FILE *file, struct tuple **tuple, uint64_t pattern_size);

static enum file_write_status write_pattern(FILE *file, struct key **pattern, size_t pattern_size);

void print_tree_header_from_file(FILE *file);
void print_tuple_array_from_file(FILE *file);

void free_test_tree_header(struct tree_header* header);

void free_test_tuple(struct tuple* tuple);

void *malloc_test(size_t size);

void free_test(void *ptr);

void print_ram();

enum file_read_status read_tree_header(struct tree_header *header, FILE *file);

#endif
