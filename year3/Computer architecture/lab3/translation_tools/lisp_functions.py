# pylint: disable=line-too-long, import-error, missing-module-docstring, ungrouped-imports
# pylint: disable=missing-function-docstring
from constants import isa_const
from translation_tools.utility import is_num
from constants.isa_const import code, terms, stack, memory_map


def choose_func():
    """Обработка текущей функции"""
    match isa_const.terms[isa_const.term_number].symbol:
        case ')':
            write_close_bracket()
        case '(':
            write_open_bracket()
        case "defvar":
            write_defvar()
        case "dotimes":
            write_dotimes()
        case "cond":
            write_cond()
        case "=":
            write_equally()
        case "mod":
            write_mod()
        case "setq":
            write_setq()
        case '+' | '-' | '/' | '*':
            write_alu(isa_const.terms[isa_const.term_number].symbol)
        case "print":
            write_print()
        case "format":
            write_format()
        case "loop":
            write_loop()
        case "read":
            write_read()


def write_close_bracket():
    isa_const.deep -= 1
    isa_const.term_number += 1


def write_open_bracket():
    isa_const.deep += 1
    isa_const.term_number += 1
    choose_func()


def write_defvar():
    var = get_args()
    memory_map[var] = len(code)
    val = get_args()
    if val == ')':
        val = ' '
    code.append({'opcode': "movv", 'arg': [memory_map[var], val]})


def write_dotimes():
    stack.extend([terms[isa_const.term_number + 2].symbol, terms[isa_const.term_number + 3].symbol, len(code)])
    isa_const.term_number += 5
    choose_func()
    idx = stack.pop(-2) - 1
    begin = code[idx]
    begin['arg'][0] = (len(code) - 1)
    code[idx] = (begin)
    idx = stack.pop() - 1
    new_command = ({'opcode': "loop", 'arg': [stack.pop(), memory_map[stack.pop()], memory_map[stack.pop()]]})
    code[idx] = new_command


def write_cond():
    idx = 0
    isa_const.term_number += 1
    cur_deep = isa_const.deep  # 2
    while isa_const.deep >= cur_deep:
        choose_func()
        if isa_const.deep == cur_deep:
            idx = stack.pop()
            begin = code[idx]
            begin["arg"][0] = len(code) + 1
            code[idx] = begin
            code.append({'opcode': "jp", 'arg': [0]})
            stack.append(len(code))
            isa_const.term_number += 1
    command = code[idx]
    command['arg'][0] = len(code) - 1
    code[idx] = command


def write_equally():
    get_args()
    code.append({'opcode': "bne", 'arg': [[get_args()]]})
    stack.append(len(code) - 1)
    isa_const.term_number += 1


def write_mod():
    arg1 = memory_map[get_args()]
    code.append({'opcode': "mod", 'arg': [arg1, get_args()]})
    isa_const.term_number += 1
    isa_const.deep -= 1


def write_setq():
    val = get_args()
    get_args()
    if code[len(code) - 1]["opcode"] != "rd":
        code.append({'opcode': "mov", 'arg': [memory_map[val]]})
    else:
        read = code.pop()
        read["arg"] = [memory_map[val]]
        code.append(read)


def write_alu(operation):
    arg = []
    while terms[isa_const.term_number + 1].symbol != ')':
        arg.append(memory_map[get_args()])
    code.append({'opcode': isa_const.symbol2opcode.get(operation).value, 'arg': arg})
    isa_const.term_number += 1
    isa_const.deep -= 1


def write_print():
    code.append({'opcode': "print", 'arg': [memory_map[get_args()]]})


def write_format():
    get_args()
    code.append({'opcode': "printf", 'arg': [memory_map[get_args()]]})


def write_loop():
    stack.append(len(code))
    cur_deep = isa_const.deep
    isa_const.term_number += 1
    while cur_deep <= isa_const.deep:
        choose_func()
        isa_const.term_number += 1
        if len(terms) == isa_const.term_number:
            break
    i = stack.pop()
    code.append({'opcode': "jp", 'arg': [i]})


def write_read():
    if code[len(code) - 1]["opcode"] == "setq":
        code.pop()
    code.append({'opcode': "rd", 'arg': []})
    isa_const.deep -= 1
    isa_const.term_number += 1


def get_args():
    """Получение аргументов для функции"""
    isa_const.term_number += 1
    if terms[isa_const.term_number].symbol == '(':
        isa_const.term_number += 1
        isa_const.deep += 1
        choose_func()
    elif is_num(terms[isa_const.term_number].symbol):
        return int(terms[isa_const.term_number].symbol)
    return terms[isa_const.term_number].symbol
