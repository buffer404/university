import get_data
from numpy import *


def input_data():
    print("Здравствуйте, выберите способ ввода данных: \n Введите 1, для ввода данных вручную \n Введите 2, "
          "для ввода данных из файла \n Введите 3, для случайной генерации чисел")
    kind = get_data.get_int()
    while (kind > 3 or kind < 1):
        print("Некорректный ввод")
        kind = get_data.get_int()
    if (kind == 1):
        return manual_input()
    elif (kind == 2):
        return file_input()
    else:
        return random_input()


def manual_input():
    print("Введите порядок матрицы не более 20")
    order = get_data.get_int()
    while (order < 0 or order > 20):
        print("Некорректный ввод")
        order = get_data.get_int()
    matrix = []
    print("Начинайте вводить значения по одному (если число дробное - используйте точку):")
    for i in range(order):
        row = []
        for j in range(order + 1):
            row.append(get_data.get_int_float())
        matrix.append(row)
    return matrix


def file_input():
    try:
        print("Введить относительный путь к файлу...")  # data/input.txt
        input_file = open(input(), 'r')
        order = int(input_file.readline())
        matrix = []
        for line in input_file:
            row = list(map(float, line.strip().split()))
            if (len(row) != (order + 1)):
                raise ValueError
            matrix.append(row)
        if (len(matrix) != order):
            raise IndexError
        return matrix
    except (IndexError):
        print("Неправильный ввод данных(")
    except (TypeError, ValueError, IndexError):
        print("Не удалось считать данные из файла")
        sys.exit()


def random_input():
    order = random.randint(0, 10)
    matrix = random.randint(-10, 10, (order, order + 1))
    while (det(matrix) == 0):
        matrix = random.randint(-10, 10, (order, order + 1))
    return matrix


def gauss(matrix):
    original_matrix = matrix
    len_matrix = len(matrix)
    for i in range((len(matrix))):
        # Поиск максимального элемента
        main_elem = i
        for m in range(i, len_matrix):
            if abs(matrix[m][i]) > abs(matrix[main_elem][i]):
                main_elem = m
        # Перестановка строк
        if main_elem != i:
            for k in range(len_matrix + 1):
                matrix[i][k], matrix[main_elem][k] = matrix[main_elem][k], matrix[i][k]

        # Нормирование
        for k in range(len_matrix, -1, -1):
            try:
                normalize = matrix[i][i]
                matrix[i][k] /= normalize
            except ZeroDivisionError:
                return 0, 0, 0, 0
        # Исключение
        for j in range(i + 1, len_matrix):
            aspect = matrix[j][i] / matrix[i][i]
            for k in range(i, len_matrix + 1):
                matrix[j][k] -= aspect * matrix[i][k]

    determinant = det_triangle(matrix)

    # Обратный ход
    answer = [0] * len_matrix
    for i in range(len_matrix - 1, -1, -1):
        local_x = matrix[i][len_matrix]
        for j in range(i + 1, len_matrix):
            local_x -= (matrix[i][j] * answer[j])
        answer[i] = local_x
    return residual(original_matrix, len_matrix, answer, determinant, matrix)


def residual(original_matrix, len_matrix, answer, determinant, matrix):
    residuals = [0] * len_matrix
    for i in range(len_matrix):
        local_residual = float64(0)
        for j in range(len_matrix):
            local_residual += original_matrix[i][j] * answer[j]
        residuals[i] = abs(float64(original_matrix[i][len_matrix] - local_residual))
    return answer, matrix, residuals, determinant


def det_triangle(matrix):
    determinant = float64(1)
    for i in range(len(matrix)):
        determinant *= matrix[i][i]
    return determinant


def det(matrix):
    if len(matrix) == 1:
        return matrix[0][0]
    else:
        determinant = float64(0)
        for i in range(len(matrix)):
            m = delete(matrix, [i], 1)
            if i % 2 == 0:
                determinant += (matrix[0][i] * det(delete(m, [0], 0)))
            else:
                determinant -= (matrix[0][i] * det(delete(m, [0], 0)))
        return determinant


def solve():
    matrix = input_data()
    print("Исходная матрица:")
    for row in matrix:
        print(' '.join([str(elem) for elem in row]))

    answer, matrix, residuals, determinant = gauss(matrix)
    if answer is None or determinant == 0:
        print("Система не имеет решений!")
    else:
        print("Вычисленная треугольная матрица равна:")
        for row in matrix:
            print(' '.join([str(elem) for elem in row]))

        print("\n Вычисленные неизвестные:")
        for i in range(len(answer)):
            print("X" + str(i + 1) + " = " + str(answer[i]))

        print("\n Определитель матрицы: " + str(determinant))

        print("\n Вычисленные невязки:")
        for i in range(len(residuals)):
            print("Delta" + str(i + 1) + " = " + str(residuals[i]))


if __name__ == '__main__':
    solve()
