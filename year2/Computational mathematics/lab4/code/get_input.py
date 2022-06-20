from sympy import *


def get_int():
    try:
        number = int(input(">> "))
        return number
    except EOFError as error:
        print("Экстренное завершение работы(")
        SystemExit
    except:
        print("Неправильный ввод, пожалуста повторите...")
        return get_int()


def get_int_float():
    try:
        number = float(input(">> "))
        return number
    except EOFError:
        print("Экстренное завершение работы(")
        SystemExit
    except:
        print("Неправильный ввод, пожалуста повторите...")
        return get_int_float()


def get_interval():
    print("Введите левую границу: ")
    a = get_int_float()
    print("Введите правую границу: ")
    b = get_int_float()
    return a, b


def get_sigma():
    print("Введите интервал:")
    return get_int_float()


def get_equation():
    print("Введите функцию")
    equation1 = input(">> ")
    print("Введите производную четвертого порядка от функции")
    equation2 = input(">> ")
    a, b = get_interval()
    print("Введите количество интервалов:")
    n = get_int()
    f1 = lambda x: eval(equation1)
    f2 = lambda x: eval(equation2)
    return f1, a, b, n, f2
