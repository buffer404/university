from test import *
from file_reader import *
from config import *
from math_tools import *


def width_search(start, end, allowed_cities=cities_names[:]):
    levels = [[start]]
    while not (end in levels[-1]):
        next_width_step(levels, allowed_cities)
    return get_result(lambda x, y: x + SEPARATOR + y, end, levels)


def find_intersection(levels, levels_rev):
    vector = set([elem for level in levels for elem in level])
    vector_rev = set([elem for level in levels_rev for elem in level])
    if len(vector.intersection(vector_rev)) < 1:
        return False
    else:
        return vector.intersection(vector_rev).pop()


def two_side_search(start, end, allowed_cities=cities_names[:]):
    ac = allowed_cities[:]
    acr = allowed_cities[:]
    levels = [[start]]
    levels_rev = [[end]]
    while not (find_intersection(levels, levels_rev)):
        next_width_step(levels, ac)
        next_width_step(levels_rev, acr)
    middle = find_intersection(levels, levels_rev)
    res1 = get_result(lambda x, y: x + SEPARATOR + y, middle, levels)
    res2 = get_result(lambda y, x: x + SEPARATOR + y, middle, levels_rev)
    res2 = (res2[0][len(middle):], res2[1])
    return (res1[0] + res2[0], res1[1] + res2[1])


def limited_depth_search(start, end, limit=BASIC_DEPTH, local_limit=0, allowed_cities=cities_names[:],
                         sort_f=lambda s: lambda x: x):
    if start in allowed_cities:
        allowed_cities.remove(start)
    if start == end:
        return (end, 0)
    elif local_limit + 1 < limit:
        neib = sort_f(start)(get_all_neib(start, allowed_cities))
        for n_named in neib:
            val = limited_depth_search(n_named, end, limit, local_limit + 1, allowed_cities[:], sort_f)
            if val:
                return (start + SEPARATOR + val[0], val[1] + distances[cities[n_named]][cities[start]])


def iterative_depth_search(start, end, limit=len(cities_names), allowed_cities=cities_names[:]):
    for i in range(1, limit):
        val = limited_depth_search(start, end, i)
        if val:
            return val


def depth_search(start, end, allowed_cities=cities_names[:]):
    return limited_depth_search(start, end, 2 ** 32)


def greed_search(start, end, allowed_cities=cities_names[:]):
    return limited_depth_search(start, end, 2 ** 32, sort_f=lambda s: (lambda x: sorted(x, key=lambda n: d_to_end[n])))


def a_search(start, end, allowed_cities=cities_names[:]):
    return limited_depth_search(start, end, 2 ** 32, sort_f=lambda s: (
        lambda x: sorted(x, key=lambda n: d_to_end[n] + distances[cities[s]][cities[n]])))


### Запуск программы ###
def main():
    read_file()
    test_not_info(START_CITY, END_CITY)
    read_file_evristica()
    test_info(START_CITY, END_CITY)


if __name__ == '__main__':
    main()