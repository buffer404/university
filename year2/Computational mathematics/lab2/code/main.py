import math
import get_data
import numpy as np


def get_system_of_equations():
    print("Здравствуйте, выберите уравнение: \n(1)\nx^2+xy-10 \ny+3xy^2-57 \n(2) \nx+xy^3-9 \nxy+xy^2-6")
    number = get_data.get_int()
    if number == 1:
        return 1, 1.5, 3.5
    else:
        return 2, 0.832, 2.243


def get_equation():
    print("Здравствуйте, выберите уравнение: \n(1) : x*sin(x)=0 \n(2) : x-5-lg(x)=0 \n(3) 2^(x)-x^(2) =0 \n(4) "
          "Пользовательский ввод")
    number = get_data.get_int()
    if number == 1:
        return lambda x: x * math.sin(x), lambda x: x * math.sin(x) + x, 3, 3.2, 0.001
    elif number == 2:
        return lambda x: x - 5 - math.log10(x), lambda x: 2 * x - 5 - math.log10(x), 4, 7, 0.001
    elif number == 3:
        return lambda x: 2 ** x - x ** 2, lambda x: 2 ** x - x ** 2 + x, 0, 3, 0.001
    else:
        return get_data.get_equation()


def bisection_method(equation, a, b, sigma):
    while b - a > sigma:
        middle = equation(a) * equation((a + b) / 2)
        if middle > 0:
            a = (a + b) / 2
        elif middle < 0:
            b = (a + b) / 2
        else:
            break
    return (a + b) / 2


def fixed_point_iteration_method(equation, a, sigma):
    dx = float(1)
    while dx * dx > sigma:
        b = a
        a = equation(b)
        dx = a - b
    return a


def df(x, y, kind):
    df = np.array([[0, 0], [0, 0]])
    if kind == 1:
        df[0][0] = 2 * x + y
        df[0][1] = x
        df[1][0] = 3 * math.pow(y, 2)
        df[1][1] = 1 + 6 * x * y
    else:
        df[0][0] = 1 + math.pow(y, 3)
        df[0][1] = 3 * x * math.pow(y, 2)
        df[1][0] = y + math.pow(y, 2)
        df[1][1] = x + 2 * x * y
    return df


def f(x, y, kind):
    f = np.array([0, 0])
    if kind == 1:
        f[0] = 10 - math.pow(x, 2) - x * y
        f[1] = 57 - y - 3 * x * math.pow(y, 2)
    else:
        f[0] = 9 - x - x * math.pow(y, 3)
        f[1] = 6 - x * y - x * math.pow(y, 2)
    return f


def newton_method(kind, x, y, sigma=0.01):
    X = np.array([x, y])
    dx = [1, 1]
    while abs(dx[0]) > sigma or abs(dx[1]) > sigma:
        X_last = X
        dx = np.dot(np.linalg.inv(df(X[0], X[1], kind)), f(X_last[0], X_last[1], kind))
        X += dx
    return X


if __name__ == '__main__':
    print("Выберите что вы хотите решить: \n(1) Нелинейное уравнение \n(2) Систему нелинейных уравнений")
    method = get_data.get_int()

    if method == 1:
        equation1, equation2, a, b, sigma = get_equation()
        try:
            answer1 = bisection_method(equation1, a, b, sigma)
            answer2 = fixed_point_iteration_method(equation2, a, sigma)
            print("Метод деления пополам:")
            print("Корень: " + str(answer1) + "\n")
            print("Метод простой итерации:")
            print("Корень: " + str(answer2) + "\n")
            print("Разница в полученных решения составляет: " + str(abs(answer2 - answer1)))
        except ValueError:
            print('Произошла арифметическая ошибка!\n')
    else:
        kind, x, y = get_system_of_equations()
        try:
            X = newton_method(kind, x, y)
            print("Вычисленные корни системы: \nx1 = " + str(X[0]) + "\nx2 = " + str(X[1]))
        except ValueError:
            print('Произошла арифметическая ошибка!\n')
