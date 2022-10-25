import random

names = []

def read_file():
    result = []
    rand_list = random.sample(range(1, 32), 6)
    rand_list.append(0)
    rand_list.sort()

    with open('DATA.csv', 'r', encoding='UTF-8') as fp:
        line = fp.readline()
        while line:
            line_arr = line[:-1].split(';')

            if line_arr[-1] == '2' or line_arr[-1] == '1' or line_arr[-1] == '3':
                line_arr[0] = '0'
            else:
                line_arr[0] = '1'

            line_arr.pop()

            cur = []
            for i in rand_list:
                cur.append(line_arr[i])
            result.append(cur)
            line = fp.readline()

    return result, rand_list


def read_attributes():
    result = []
    with open('attributes.txt', 'r', encoding='UTF-8') as fp:
        line = fp.readline()
        while (line):
            name = line.split(':')[0][8:]
            result.append(name)
            line = fp.readline()
    return result


def freq(T, C):
    count = 0
    for line in T:
        if line[0] == C:
            count += 1
    return count

# Получение массива значений для мажорного
def table_partition(T, X):
    dict_tables = dict()
    for line in T:
        if dict_tables.get(line[X]):
            dict_tables[line[X]].append(line)
        else:
            dict_tables[line[X]] = [line]

    result = []
    for key in dict_tables:
        result.append(dict_tables[key])
    return result


def get_classes(T):
    classes = set()
    for line in T:
        classes.add(line[0])
    return classes


def is_one_classed(T):
    """
    Returns True if all elements of
    table _T_ have the same class
    """
    for line in T:
        if line[0] != T[0][0]:
            return False
    return True


def del_col(T, X):
    """
    Sets the _X_ column to None for all
    elements of table _T_
    """
    for line in T:
        line[X] = None
    return T


def get_major_class(T):
    classes = dict.fromkeys(get_classes(T), 0)
    max = (0, 0)
    for line in T:
        classes[line[0]] += 1
        if max[1] < classes[line[0]]:
            max = (line[0], classes[line[0]])
    #    print('e=%s p=%s e/p=%s p/e=%s max=%s' % (classes['e'],classes['p'],classes['e']/classes['p'],classes['p']/classes['e'], max[0]))
    return (max[0], classes[max[0]] / sum(classes.values()), sum(classes.values()))


#
def collect_chances(tree):
    chances = []
    for node in tree:
        if node['class']:
            chances.append(
                {'chance': node['chance'], 'class': 1 if node['class'] == '0' else 0, 'count': node['count']})
        else:
            chances.extend(collect_chances(node['childs']))
    return sorted(chances, key=lambda x: x['chance'], reverse=True)