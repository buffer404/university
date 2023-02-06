# pylint: disable=missing-module-docstring
# pylint: disable=import-error
import json
from collections import namedtuple
from constants.isa_const import Opcode


class Term(namedtuple('Term', 'pos symbol')):
    """Описание выражения из исходного текста программы."""
    # сделано через класс, чтобы был docstring


def write_code(filename, code):
    """Записать машинный код в файл."""
    with open(filename, "w", encoding="utf-8") as file:
        file.write(json.dumps(code, indent=4))
        code.clear()


def read_code(filename):
    """Прочесть машинный код из файла."""
    with open(filename, encoding="utf-8") as file:
        code = json.loads(file.read())

    for instr in code:
        instr['opcode'] = Opcode(instr['opcode']).value

    return code
