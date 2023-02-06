# pylint: disable=missing-class-docstring     # чтобы не быть Капитаном Очевидностью
# pylint: disable=missing-function-docstring  # чтобы не быть Капитаном Очевидностью
# pylint: disable=import-error  # не видит мои модули
# pylint: disable=line-too-long
"""Модульный тест транслятора
"""

import unittest
import translator
from constants import isa_const


class TestTranslator(unittest.TestCase):

    def test_translation_err1(self):
        with open("examples/err1.lisp", "rt", encoding="utf-8") as file:
            text = file.read()

        try:
            translator.translate(text)
            self.fail('validator verification test 1 failed')
        except AssertionError:
            isa_const.deep = 0
            isa_const.code.clear()
            isa_const.terms.clear()
            isa_const.term_number = 0

    def test_translation_err2(self):
        with open("examples/err2.lisp", "rt", encoding="utf-8") as file:
            text = file.read()

        try:
            translator.translate(text)
            self.fail('validator verification test 2 failed')
        except AssertionError:
            isa_const.deep = 0
            isa_const.code.clear()
            isa_const.terms.clear()
            isa_const.term_number = 0
