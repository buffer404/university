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