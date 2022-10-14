### Входные данные ###
FILE_NAME = 'map.data'
FILE_NAME_2 = 'coords.data'
FILE_ENCODING = 'UTF-8'
START_CITY = 'Самара'
END_CITY = 'Ярославль'
SEPARATOR = ' -> '
BASIC_DEPTH = 8
### Переменные для навигации по графу ###
cities_names =[
                'Вильнюс', 'Витебск', 'Воронеж', 'Волгоград',
                'Калининград', 'Каунас', 'Киев', 'Житомир', 'Кишинев',
                'С.Петербург', 'Москва', 'Мурманск', 'Орел',
                'Одесса', 'Рига', 'Таллин', 'Харьков', 'Ярославль',
                'Уфа', 'Брест', 'Ниж.Новгород', 'Даугавпилс', 'Донецк',
                'Казань', 'Минск', 'Симферополь', 'Самара'
                ]
cities = { cities_names[i]  :   i    for i in range(len(cities_names))}
cities_rev = { i   :   cities_names[i]   for i in range(len(cities_names))}
distances = [ [ 0 for i in range(len(cities_names)) ]    for i in range(len(cities_names)) ]
coordinates = { cities_names[i]  :   (1, 1)    for i in range(len(cities_names))}
d_to_end = { cities_names[i]  :   1    for i in range(len(cities_names))}