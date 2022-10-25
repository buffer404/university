import math
from utils import get_classes, freq, table_partition


def info(T):
    sum = 0
    classes = get_classes(T)
    for class_ in classes:
        sum += ( freq(T, class_) * math.log2( freq(T, class_) / len(T)) )  / len(T)
    return sum * (-1)


def info_x(T, X):
    sum = 0
    tables = table_partition(T, X)
    for subtable in tables:
        sum += ( len(subtable) * info(subtable) ) / len(T)
    return sum


def split_info_x(T, X):
    sum = 0
    tables = table_partition(T, X)
    for subtable in tables:
        sum += ( len(subtable) * math.log2(len(subtable) / len(T)) ) / len(T)
    return sum * (-1)


def gain_ratio(T, X):
    try:
        return ( info(T) - info_x(T, X) ) / split_info_x(T, X)
    except ZeroDivisionError:
        return 0

# Получение максимального "Нормированного прироста информации"
def max_gain(T):
    max = (0, 0)
    is_empty = True
    for i in range(1, len(T[0])):
        if T[0][i] != None and gain_ratio(T, i) > max[1]:
            is_empty = False
            max = (i, gain_ratio(T, i))
    if is_empty:
        return None
    return max[0]