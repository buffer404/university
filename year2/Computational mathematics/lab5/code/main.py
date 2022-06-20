import math
import numpy as np
import matplotlib.pyplot as plt
import get_input


def get_data(funk=None, clear_funk=None, h=None, b=None):
    print("Здравствуйте, пожалуйсла выберите функцию: \n"
          "(1) y' = x + y, y(0) = 1\n"
          "(2) y' = x * y / x + x^3 + x, y(1) = 3 \n"
          "(3) y' = x * 3 + y + 1, y(0) = 1 \n"
          "(4) y' = x * 3 + y + 1, y(0) = -1 \n"
          "(5) y' = y + sin(x), y(0) = 0 \n"
          "(6) y' = y + sin(x), y(0) = 1 \n")
    kind = get_input.get_int()
    x = []
    y = []
    if kind == 1:
        funk = lambda x, y: x + y
        x = [0, 0.1, 0.2, 0.3]
        y = [1, 1.11034, 1.24280, 1.39971]
        h = 0.1
        clear_funk = lambda x: 2 * math.pow(math.e, x) - x - 1
    elif kind == 2:
        funk = lambda x, y: 3*y/x + math.pow(x, 3) +x
        x = [1, 1.1, 1.2, 1.3]
        y = [3, 4.2471, 5.8176, 7.7571]
        h = 0.1
        clear_funk = lambda x: math.pow(x, 2) * (math.pow(x, 2) + 3*x -1)
    elif kind == 3:
        funk = lambda x, y: x * 3 + y + 1
        x = [0, 0.1, 0.2, 0.3]
        y = [-1, -0.9844, -0.935792, -0.850424]
        h = 0.1
        clear_funk = lambda x: -3 * x + 3 * math.pow(math.e, x) - 4
    elif kind == 4:
        funk = lambda x, y: x * 3 + y + 1
        x = [0, 0.1, 0.2, 0.3]
        y = [1, 1.22585, 1.50701, 1.84929]
        h = 0.1
        clear_funk = lambda x: -3 * x + 5 * math.pow(math.e, x) - 4
    elif kind == 5:
        funk = lambda x, y: y + math.sin(x)
        x = [-5, -4.9, -4.8, -4.7]
        y = [-0.617924, -0.580759, -0.537717, -0.48922]
        h = 0.1
        clear_funk = lambda x: 0.5*(math.pow(math.e, x)-math.cos(x)-math.sin(x))
    elif kind == 6:
        funk = lambda x, y: y + math.sin(x)
        x = [-5, -4.9, -4.8, -4.7]
        y = [-0.611186, -0.573313, -0.529487, -0.480124]
        h = 0.1
        clear_funk = lambda x: 0.5*(3*math.pow(math.e, x)-math.cos(x)-math.sin(x))
    print("Дифференцирование функции от ", x[0], " до:")
    b=get_input.get_int_float()
    return funk, x, y, clear_funk, h, b


def method_milna(funk, x, y, h, b):
    new_y = []
    new_x = []
    for j in y:
        new_y.append(j)
    for i in range(round((b-x[0])/h)+1):
        new_x.append(x[0]+h*i)
    for i in range(round((b-x[len(x)-1])/h)):
        # Предсказание
        predict_y = y[0] + 4 * h / 3 * (
                2 * funk(x[1], y[1]) - funk(x[2], y[2]) + 2 * funk(
            x[3], y[3]))
        # Коррекция
        cur_y = y[2] + h / 3 * (
                funk(x[2], y[2]) + 4 * funk(x[3], y[3]) + funk(x[len(x)-1], predict_y))
        x.pop(0)
        x.append(x[len(x)-1]+h)
        y.pop(0)
        y.append(cur_y)
        new_y.append(cur_y)
    return new_x, new_y


def divided_differences(x_values, y_values, k):
    result = 0
    for j in range(k + 1):
        mul = 1
        for i in range(k + 1):
            if i != j:
                mul *= x_values[j] - x_values[i]
        result += y_values[j] / mul
    return result


def create_newton_polynomial(x_values, y_values):
    div_diff = []
    for i in range(1, len(x_values)):
        div_diff.append(divided_differences(x_values, y_values, i))

    def newton_polynomial(x):
        result = y_values[0]
        for k in range(1, len(y_values)):
            mul = 1
            for j in range(k):
                mul *= (x - x_values[j])
            result += div_diff[k - 1] * mul
        return result

    return newton_polynomial


def show_plot(x, y, clear_x, clear_y):
    fig, ax = plt.subplots()
    ax.plot(x, y, color="blue", label="Интерполяционный полином")
    ax.plot(clear_x, clear_y, color="red", label="Исходная функция")
    ax.grid()
    ax.set_xlabel("x")
    ax.set_ylabel("y")
    # ax.legend(loc=4)
    plt.show()


if __name__ == '__main__':
    funk, original_x, original_y, clear_funk, h, b = get_data()
    new_x, new_y = method_milna(funk, original_x, original_y, h, b)
    polynomial = create_newton_polynomial(new_x, new_y)
    polynomial_y = []
    clear_y = []
    x = np.arange(new_x[0], new_x[len(new_x)-1], h)
    for i in x:
        polynomial_y.append(polynomial(i))
        clear_y.append(clear_funk(i))

    show_plot(x, polynomial_y, x, clear_y)