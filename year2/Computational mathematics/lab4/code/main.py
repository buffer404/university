<<<<<<< HEAD
# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
=======
import math
from numpy import arange
import matplotlib.pyplot as plt
import get_input


def get_data(funk=None, x=None, y=None):
    print("Здравствуйте, пожалуйсла выберите функцию: \n"
          "(1) y = x \n(2) y = x^2 \n(3) y = lg(x) \n(4) y = sin(x)")
    kind = get_input.get_int()
    if kind == 1:
        funk = lambda x: x
        x = [-5, -4.5, -4, -3.5, -3, -2.5, -2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
        y = [-5, -4.5, -3.5, -3.5, -3, -2.5, -2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
    elif kind == 2:
        funk = lambda x: x * x
        x = [-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]
        y = [25, 16, 9, 4, 1, 0, 1, 4, 6, 16, 25]
    elif kind == 3:
        funk = lambda x: math.log10(x)
        x = [0.3, 0.5, 1, 2, 3, 4, 5]
        y = [-0.5, -0.3, 0, 0.3, 0.4, 0.6, 0.7]
    elif kind == 4:
        funk = lambda x: math.sin(x)
        x = [-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]
        y = [0.95, 0.75, 0.2, -0.9, -0.84, 0, 0.84, 0.9, 0.14, -0.75, -0.95]
    return funk, x, y


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
    funk, x, y = get_data()
    clear_x = arange(x[0], x[len(x) - 1] + 0.1, 0.1)
    clear_y = []
    for i in clear_x:
        clear_y.append(funk(i))

    newton_pol = create_newton_polynomial(x, y)
    newton_y = []
    for i in clear_x:
        newton_y.append(newton_pol(i))

    show_plot(clear_x, newton_y, clear_x, clear_y)
>>>>>>> 0c1c239b235a2d06e4480776d2bfb4f264e97281
