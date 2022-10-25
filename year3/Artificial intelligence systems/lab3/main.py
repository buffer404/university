from copy import deepcopy
from utils import *
from test import test_all
from c45 import max_gain

import numpy as np
import matplotlib.pyplot as plt

from sklearn.metrics import roc_curve, precision_recall_curve


def build_tree(T):
    """
    An entry point in C45 algorithm.
    _T_ - a two-dimensional array representing data table in the following format:
    [
        ['<class value>', '<param1>', '<param2>', ...],
        ['<class value>', '<param1>', '<param2>', ...],
        ['<class value>', '<param1>', '<param2>', ...],
        ...
    ]
    Returns the tree in array format.
    """
    col = max_gain(T)
    if col == None:
        return {'value': get_major_class(T), 'key': 'leaf'}
    tree = []
    subtables = table_partition(T, col)
    for subtable in subtables:
        v = subtable[0][col]

        if is_one_classed(subtable):
            tree.append({'attr': col, 'value': v, 'class': subtable[0][0], 'chance': 1, 'count': len(subtable)})
        else:
            subtable = del_col(subtable, col)
            subtree = build_tree(subtable)
            if subtree['key'] == 'leaf':
                tree.append({'attr': col, 'value': v, 'class': subtree['value'][0], 'chance': subtree['value'][1], 'count': subtree['value'][2]})
            else:
                tree.append({'attr': col, 'value': v, 'class': False, 'childs': subtree['value']})

    return {'value': tree, 'key': 'tree'}


names = read_attributes()


def plot_roc_curve(true_y, y_prob):
    """
    plots the roc curve based of the probabilities
    """

    fpr, tpr, thresholds = roc_curve(true_y, y_prob)
    plt.plot(fpr, tpr)
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.show()


def plot_pr_curve(true_y, y_prob):
    precision, recall, thresholds = precision_recall_curve(true_y, y_prob)
    plt.plot(recall, precision)
    plt.xlabel("Recall")
    plt.ylabel("Percision")
    plt.show()


if __name__ == '__main__':
    # Получаем данные из файла и атрибуты
    table, rand_list = read_file()
    test_table = deepcopy(table)

    # Создаем дерево решений
    tree = build_tree(table)['value']

    # Получаем таблицу с классом, и количеством объектов в данном классе на каждом шаге
    roc_table = collect_chances(tree)

    y = np.array([])
    y_prob = np.array([])
    for row in roc_table:
        y = np.append(y, [row['class']] * row['count'])
        y_prob = np.append(y_prob, [row['chance']] * row['count'])


    plot_pr_curve(y, y_prob)
    plt.close()
    plot_roc_curve(y, y_prob)
    test_all(tree, test_table)