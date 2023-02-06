# pylint: disable=missing-class-docstring
# pylint: disable=missing-function-docstring
# pylint: disable=missing-module-docstring
# pylint: disable=line-too-long
from sys import argv
from translation_tools.utility import is_word, is_num
from translation_tools.lisp_functions import choose_func
from constants.isa import Term, write_code
from constants import isa_const


def translate(text):
    """Транслируем текст в последовательность значимых термов."""

    for line in text.split('\n'):
        for pos, word in enumerate(line.split(), 1):
            if (word in isa_const.correct_words) or (is_num(word) == 1) or (is_word(word) == 1):
                isa_const.terms.append(Term(pos, word))

    for term in isa_const.terms:
        if term.symbol == '(':
            isa_const.deep += 1
        if term.symbol == ')':
            isa_const.deep -= 1
        assert isa_const.deep >= 0, "Unbalanced brackets!"
    assert isa_const.deep == 0, "Unbalanced brackets!"

    while isa_const.term_number != len(isa_const.terms):
        if isa_const.terms[isa_const.term_number].symbol == '(':
            isa_const.deep += 1
            isa_const.term_number += 1
            choose_func()
            if len(isa_const.terms) == isa_const.term_number:
                break
            isa_const.term_number += 1
        elif isa_const.terms[isa_const.term_number].symbol == ')':
            isa_const.deep -= 1
            isa_const.term_number += 1
    isa_const.code.append({'opcode': "halt"})

    return isa_const.code


def main(args):
    assert len(args) == 2, \
        "Wrong arguments: translation_tools.py <input_file> <target_file>"
    source, target = args

    with open(source, "rt", encoding="utf-8") as file:
        source = file.read()
    code = translate(source)
    print("source LoC:", len(source.split()), "code instr:", len(code))
    isa_const.deep = 0
    write_code(target, code)


if __name__ == '__main__':
    main(argv[1:])
