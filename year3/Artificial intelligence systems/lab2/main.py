cities_names = ['Вильнюс', 'Витебск', 'Воронеж', 'Волгоград', 'Калининград', 'Каунас', 'Киев', 'Житомир', 'Кишинев',
                'С.Петербург', 'Москва', 'Мурманск', 'Орел', 'Одесса', 'Рига', 'Таллин', 'Харьков', 'Ярославль',
                'Уфа', 'Брест', 'Ниж.Новгород', 'Даугавпилс', 'Донецк', 'Казань', 'Минск', 'Симферополь', 'Самара']
cities = {cities_names[i]: i for i in range(len(cities_names))}
distances = [[0 for i in range(len(cities_names))] for i in range(len(cities_names))]


def make_distance(city_1, city_2, dist):
    distances[cities[city_1]][cities[city_2]] = dist
    distances[cities[city_2]][cities[city_1]] = dist


with open('data.txt', 'r', encoding='UTF-8') as fp:
    line = fp.readline()
    while (line):
        city1 = line[:-1].split(', ')[0]
        city2 = line[:-1].split(', ')[1]
        dist = int(line[:-1].split(', ')[2])
        make_distance(city1, city2, dist)
        line = fp.readline()


def printMatrix(matrix):
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            print("{:5d}".format(matrix[i][j]), end="")
        print()


def depth_search(start, end):
    result = ''
    while(result != end):
