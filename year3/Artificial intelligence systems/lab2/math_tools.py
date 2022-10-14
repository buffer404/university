from config import *

### Получение всех соседей вершины ###
def get_all_neib(start, allowed_cities):
    neib = distances[cities[start]][:]
    neib_named = []
    for n in range(len(neib)):
        if neib[n] != 0 and cities_rev[n] in allowed_cities:
            neib_named.append(cities_rev[n])
    return neib_named

def next_width_step(levels, allowed_cities):
    cur_level = levels[-1]
    levels.append([])
    for city in cur_level:
        for n_named in get_all_neib(city, allowed_cities):
            levels[-1].append(n_named)
            allowed_cities.remove(n_named)

def get_result(conc_res, end, levels):
    result = (end, 0)
    for level in reversed(levels[:-1]):
        flag = True
        for city in level:
            if flag:
                neib = distances[cities[city]][:]
                for n in range(len(neib)):
                    if neib[n] != 0 and cities_rev[n] == end:
                        result =  (conc_res(city, result[0]), result[1] + distances[n][cities[city]])
                        end = city
                        flag = False
    return result