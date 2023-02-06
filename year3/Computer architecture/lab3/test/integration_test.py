# pylint: disable=missing-class-docstring     # чтобы не быть Капитаном Очевидностью
# pylint: disable=missing-function-docstring  # чтобы не быть Капитаном Очевидностью
# pylint: disable=line-too-long               # строки с ожидаемым выводом

"""Интеграционные тесты транслятора и машины
"""

import contextlib
import io
import os
import tempfile
import unittest

import machine
import translator


class TestTranslator(unittest.TestCase):

    def test_hello(self):
        # Создаём временную папку для скомпилированного файла. Удаляется автоматически.
        with tempfile.TemporaryDirectory() as tmpdirname:
            source = "examples/hello.lisp"
            target = os.path.join(tmpdirname, "machine.out")

            # Собираем весь стандартный вывод в переменную stdout.
            with contextlib.redirect_stdout(io.StringIO()) as stdout:
                translator.main([source, target])
                machine.main([target, target])

            # Проверяем, что было напечатано то, что мы ожидали.
            self.assertEqual(stdout.getvalue(),
                             'source LoC: 131 code instr: 23\nhello world\ninstr_counter:  22 ticks: 77\n')

    def test_cat(self):
        with tempfile.TemporaryDirectory() as tmpdirname:
            source = "examples/cat.lisp"
            target = os.path.join(tmpdirname, "machine_code.out")
            input_stream = "examples/input.txt"

            with contextlib.redirect_stdout(io.StringIO()) as stdout:
                # Собираем журнал событий по уровню INFO в переменную logs.
                with self.assertLogs('', level='INFO') as logs:
                    translator.main([source, target])
                    machine.main([target, input_stream])

            self.assertEqual(stdout.getvalue(),
                             'source LoC: 20 code instr: 5\nHello World from input!\ninstr_counter:  70 ticks: 189\n')

            self.assertEqual(logs.output,
                             ['WARNING:root:Input buffer is empty!',
                              "INFO:root:output_buffer: 'Hello World from input!'"])

    def test_cat_trace(self):
        with tempfile.TemporaryDirectory() as tmpdirname:
            source = "examples/cat.lisp"
            target = os.path.join(tmpdirname, "machine_code.out")
            input_stream = "examples/foo_input.txt"

            with contextlib.redirect_stdout(io.StringIO()) as stdout:
                with self.assertLogs('', level='DEBUG') as logs:
                    translator.main([source, target])
                    machine.main([target, input_stream])

            self.assertEqual(stdout.getvalue(),
                             'source LoC: 20 code instr: 5\nfoo\n\ninstr_counter:  13 ticks: 37\n')

            expect_log = [
                "DEBUG:root:{TICK: 0, PC: 0, ADDR: 0, OUT: 0, AC: 0, DR: 0} movv [0, 0]",
                "DEBUG:root:{TICK: 4, PC: 1, ADDR: 0, OUT: 0, AC: 0, DR: 0} rd [0]",
                "DEBUG:root:input: 'f'",
                "DEBUG:root:{TICK: 7, PC: 2, ADDR: 0, OUT: 102, AC: 0, DR: 0} printf [0]",
                "DEBUG:root:output: '' << 'f'",
                "DEBUG:root:{TICK: 10, PC: 3, ADDR: 0, OUT: 102, AC: 0, DR: 0} jp [1]",
                "DEBUG:root:{TICK: 12, PC: 1, ADDR: 0, OUT: 102, AC: 0, DR: 0} rd [0]",
                "DEBUG:root:input: 'o'",
                "DEBUG:root:{TICK: 15, PC: 2, ADDR: 0, OUT: 111, AC: 0, DR: 0} printf [0]",
                "DEBUG:root:output: 'f' << 'o'",
                "DEBUG:root:{TICK: 18, PC: 3, ADDR: 0, OUT: 111, AC: 0, DR: 0} jp [1]",
                "DEBUG:root:{TICK: 20, PC: 1, ADDR: 0, OUT: 111, AC: 0, DR: 0} rd [0]",
                "DEBUG:root:input: 'o'",
                "DEBUG:root:{TICK: 23, PC: 2, ADDR: 0, OUT: 111, AC: 0, DR: 0} printf [0]",
                "DEBUG:root:output: 'fo' << 'o'",
                "DEBUG:root:{TICK: 26, PC: 3, ADDR: 0, OUT: 111, AC: 0, DR: 0} jp [1]",
                "DEBUG:root:{TICK: 28, PC: 1, ADDR: 0, OUT: 111, AC: 0, DR: 0} rd [0]",
                "DEBUG:root:input: '\\n'",
                "DEBUG:root:{TICK: 31, PC: 2, ADDR: 0, OUT: 10, AC: 0, DR: 0} printf [0]",
                "DEBUG:root:output: 'foo' << '\\n'",
                "DEBUG:root:{TICK: 34, PC: 3, ADDR: 0, OUT: 10, AC: 0, DR: 0} jp [1]",
                "DEBUG:root:{TICK: 36, PC: 1, ADDR: 0, OUT: 10, AC: 0, DR: 0} rd [0]",
                "WARNING:root:Input buffer is empty!",
                "INFO:root:output_buffer: 'foo\\n'"]
            self.assertEqual(logs.output, expect_log)

    def test_many_add(self):
        with open("examples/many_add_test.lisp", "rt", encoding="utf-8") as file:
            text = file.read()

        code = translator.translate(text)
        print(code)
        self.assertEqual([{'opcode': 'movv', 'arg': [0, 1]},
                          {'opcode': 'movv', 'arg': [1, 2]},
                          {'opcode': 'movv', 'arg': [2, 3]},
                          {'opcode': 'movv', 'arg': [3, 4]},
                          {'opcode': 'movv', 'arg': [4, 0]},
                          {'opcode': 'add', 'arg': [0, 1, 2, 3]},
                          {'opcode': 'mov', 'arg': [4]},
                          {'opcode': 'print', 'arg': [4]},
                          {'opcode': 'halt'}],
                         code)

        with tempfile.TemporaryDirectory() as tmpdirname:
            source = "examples/many_add_test.lisp"
            target = os.path.join(tmpdirname, "machine.out")

            # Собираем весь стандартный вывод в переменную stdout.
            with contextlib.redirect_stdout(io.StringIO()) as stdout:
                translator.main([source, target])
                machine.main([target, target])

            # Проверяем, что было напечатано то, что мы ожидали.
            self.assertEqual(stdout.getvalue(),
                             'source LoC: 40 code instr: 18\n10\ninstr_counter:  8 ticks: 35\n')
