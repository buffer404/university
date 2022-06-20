from numpy import arange
import math
import get_input


def get_data(funk=None, d_funk=None):
    print("Здавствуйте, выберите уравнение для вычисления интеграла:\n"
          "(1): sin(x)\n"
          "(2): x^3\n"
          "(3): sin(x) / x\n"
          "(4): 1 / x\n"
          "(5): Пользовательский ввод")
    kind = get_input.get_int()
    if kind == 1:
        funk = lambda x: math.sin(x)
        d_funk = lambda x: math.sin(x)
    elif kind == 2:
        funk = lambda x: x * x * x
        d_funk = lambda x: 0 * x
    elif kind == 3:
        funk = lambda x: math.sin(x) / x
        d_funk = lambda x: x * math.pow(x, -4) * (
                4 * x * math.cos(x) - 12 * math.sin(x) + 24 * math.sin(x) * math.pow(x, -2) - 24 * math.cos(
            x) * math.pow(x, -1) + math.sin(x) * math.pow(x, 2))
    elif kind == 4:
        funk = lambda x: 1 / x
        d_funk = lambda x: 24 * math.pow(x, -5)
    else:
        return get_input.get_equation()
    a, b = get_input.get_interval()
    return funk, a, b, get_input.get_sigma(), d_funk


def get_r(a, b, n, d_funk):
    r = 0
    for i in arange(a, b, ((b - a) / 1000)):
        try:
            cur_r = d_funk(i)
            cur_r *= math.pow((b - a), 5)
            cur_r /= (2880 * math.pow(n, 4))
        except ValueError:
            cur_r = 0
        if abs(cur_r) > r:
            r = abs(cur_r)
    return r


def print_gap(x):
    print("Был найден разрыв функции в точке: " + str(x) + "\nВыполнется расчёт левой части интеграла от разрыва и ")


def simpson(funk, a, b, n, d_funk):
    integral = 0
    h = (b - a) / n

    try:
        integral += funk(a)
    except ZeroDivisionError:
        print_gap(a)
    try:
        integral += funk(b)
    except ZeroDivisionError:
        print_gap(b)

    for i in range(1, n):
        k = 2 + 2 * (i % 2)
        try:
            integral += k * funk(a + i * h)
        except ZeroDivisionError:
            print_gap(a + i * h)
    integral *= h / 3

    if get_r(a, b, n, d_funk) >= abs(integral):
        raise IOError("Был найден разрыв второго рода, не удалось подсчитать интеграл!")
    else:
        return integral


if __name__ == '__main__':
    funk, a, b, n, d_funk = get_data()
    print("Подсчитанный интеграл от функции " + "на интервале от " + str(a) + " до " + str(b) + " равен: ")
    print(simpson(funk, a, b, n, d_funk))
