from geopy.distance import geodesic
from math import trunc
from config import *
### Функция заполнения матрицы по названию городов ###
def make_distance(city_1, city_2, dist):
    distances[cities[city_1]][cities[city_2]] = dist
    distances[cities[city_2]][cities[city_1]] = dist
### Чтение из файла ###
def read_file():
    with open(FILE_NAME, 'r', encoding=FILE_ENCODING) as fp:
        line = fp.readline()
        while (line):
            splited_line = line[:-1].split(', ')
            city1 = splited_line[0]
            city2 = splited_line[1]
            dist = int(splited_line[2])
            make_distance(city1, city2, dist)
            line = fp.readline()
### Чтение из файла с эвристикой###
def read_file_evristica(end = END_CITY):
    with open(FILE_NAME_2, 'r', encoding=FILE_ENCODING) as fp:
        line = fp.readline()
        while (line):
            splited_line = line[:-1].split(', ')
            city = splited_line[0]
            coordinates[city] = (float(splited_line[1]), float(splited_line[2]))
            line = fp.readline()
        for key in d_to_end.keys():
            d_to_end[key] = trunc(geodesic(coordinates[end], coordinates[key]).km)