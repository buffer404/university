# pylint: disable=missing-class-docstring, invalid-name
# pylint: disable=missing-function-docstring
# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long
from enum import Enum

#  Маска для переполнения чисел
MUSK_NUMBER = 2 ** 32 - 1

# Массивы машинного кода и токенов
code = []
terms = []

# Текущий номер обрабатываемого токена
term_number = 0

# Количество незакрытых скобок
deep = 0

memory_map = {}
stack = []


class Opcode(str, Enum):
    """Opcode для ISA."""
    OPEN_BRACKET = '('
    CLOSE_BRACKET = ')'
    PRINT = 'print'
    APOSTROPHE = '\'*\''
    DEFVAR = "movv"
    OR = "or"
    EQ = "bne"
    MOD = "mod"
    SETQ = "mov"
    PLUS = 'add'
    DOTIMES = 'loop'
    COND = "cond"
    JP = "jp"
    MINUS = "sub"
    FORMAT = "printf"
    LOOP = "infloop"
    READ = "rd"
    HALT = 'halt'
    MUL = "mul"
    DIV = "div"


# Список всех спец символов
correct_words = ["(", ")", "defvar", "dotimes", "cond", "=", "mod", "setq", "+", "-", "/", "*", "print", "format",
                 "loop", "read"]

"""Cловарь символов, непосредственно транслируемых в машинный код"""
symbol2opcode = {
    "print": Opcode.PRINT,
    "\'*\'": Opcode.APOSTROPHE,
    "defvar": Opcode.DEFVAR,
    "or": Opcode.OR,
    "=": Opcode.EQ,
    "mod": Opcode.MOD,
    "setq": Opcode.SETQ,
    '+': Opcode.PLUS,
    '-': Opcode.MINUS,
    '/': Opcode.DIV,
    '*': Opcode.MUL,
    'dotimes': Opcode.DOTIMES,
    "cond": Opcode.COND,
    "format": Opcode.FORMAT,
    "loop": Opcode.LOOP,
    "read": Opcode.READ
}
