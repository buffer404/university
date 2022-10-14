from main import width_search, depth_search, two_side_search, limited_depth_search, iterative_depth_search, greed_search, a_search
from config import *
### Основные тесты по варианту ###
def test_not_info(start, end):
    print("Неинформированный поиск")
    print("_______________________________________________________")
    print("Тестируем поиск в ширину:")
    test_func(width_search, start, end)
    print("_______________________________________________________")
    print("Тестируем поиск в глубину:")
    test_func(depth_search, start, end)
    print("_______________________________________________________")
    print("Тестируем двусторонний поиск:")
    test_func(two_side_search, start, end)
    print("_______________________________________________________")
    print("Тестируем поиск в глубину с ограничением:")
    test_func(limited_depth_search, start, end)
    print("_______________________________________________________")
    print("Тестируем поиск в глубину с итерацией:")
    test_func(iterative_depth_search, start, end)
    print("_______________________________________________________")
    print("")

def test_info(start, end):
    print("Информированный поиск")
    print("_______________________________________________________")
    print("Тестируем жадный поиск:")
    test_func(greed_search, start, end)
    print("_______________________________________________________")
    print("Тестируем поиск A*:")
    test_func(a_search, start, end)
    print("_______________________________________________________")
    print("")

def test_func(f, start, end):
    res = f(start, end)
    if res:
        print("Путь: " + res[0])
        print("Расстояние: " + str(res[1]))
    else:
        print("Результат не найден")