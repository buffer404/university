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
    print("Введите левую границу интервала: ")
    a = get_int_float()
    print("Введите правую границу интервала: ")
    b = get_int_float()
    return a, b

def get_sigma():
    print("Введите погрешность (пример: 0.01 | 0.001)")
    return get_int_float()

def get_equation():
    print("Введите уравнение")
    equation1 = input(">> ")
    equation2 = equation1+" + x"
    a, b = get_interval()
    f1 = lambda x: eval(equation1)
    f2 = lambda x: eval(equation2)
    return f1, f2, a, b, get_sigma()
