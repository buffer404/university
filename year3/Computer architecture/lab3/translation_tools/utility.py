# pylint: disable=missing-module-docstring
import re


def is_word(word):
    """Проверка на то, что поданный аргумент слово"""
    match = re.search(r'\w+', word)
    if match:
        return 1
    return 0


def is_num(word):
    """Проверка на то, что поданный аргумент число"""
    try:
        int(word)
        return 1
    except ValueError:
        return 0
