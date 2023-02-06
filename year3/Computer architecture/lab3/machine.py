# pylint: disable=missing-class-docstring, disable=missing-function-docstring, line-too-long
# pylint: disable=missing-module-docstring, too-many-instance-attributes, invalid-name, consider-using-f-string
import sys
import logging
from math import copysign
from constants import isa, isa_const


class DataPath:

    def __init__(self, data_memory_size, input_buffer):
        assert data_memory_size > 0, "Data_memory size should be non-zero"
        self.data_memory_size = data_memory_size
        self.data_memory = [0] * data_memory_size
        self.data_address = 0
        self.acc = 0
        self.dr = 0
        self.input_buffer = input_buffer
        self.output_buffer = []
        self.max_data_value = 2147483647
        self.min_data_value = -2147483648

    def latch_data_addr(self, new_addr):
        """ Данная функция позволяет загружать новые значения (адреса) в регистр адреса """
        assert 0 <= new_addr < self.data_memory_size, \
            f"out of memory: {format(self.data_address)}"

        self.data_address = new_addr

    def latch_dr(self, sel, arg, op_type):
        """ Защелкнуть DR = прочитать данные из памяти
            sel = 1 Считать данные из декодера инструкций
            sel = 2 Считать данные из текущего адреса памяти
            sel = 3 Защелкнуть данные с АЛУ"""
        if sel == 1:
            self.dr = arg
        elif sel == 2:
            self.dr = self.data_memory[self.data_address]
        else:
            self.dr = self.alu(op_type)

    def latch_acc(self, op_type):
        """Защелкнуть новое значение AC, новое значение приходит из ALU
            в зависимости от входного сигнала выбирается операция на ALU"""
        self.acc = self.alu(op_type)

    def alu(self, op_type):
        cur_value = 0
        match op_type:
            case "add":
                cur_value = self.data_memory[self.data_address] + self.dr
            case "mul":
                cur_value = self.data_memory[self.data_address] * self.dr
            case "sub":
                cur_value = self.data_memory[self.data_address] - self.dr
            case "div":
                cur_value = self.data_memory[self.data_address] / self.dr
            case "mod":
                cur_value = self.data_memory[self.data_address] % self.dr
            case isa_const.Opcode.DEFVAR:
                cur_value = self.dr
            case "inc":
                cur_value = self.dr + 1

        # Проверка на переполнение
        if abs(cur_value) > self.max_data_value:
            cur_value = copysign(abs(cur_value) & isa_const.MUSK_NUMBER, cur_value)

        return cur_value

    def output(self, data_type):
        """ Вывод:
        Если data_type True : вывод числа
        Если data_type False : вывод символа производится через ASCII-символы по спецификации
        - вывод осуществляется просто в буфер. """

        if data_type:
            logging.debug('output: %s << %s', repr(
                ''.join(self.output_buffer)), repr(str(self.data_memory[self.data_address])))
            self.output_buffer.append(str(self.data_memory[self.data_address]))
        else:
            symbol = chr(self.data_memory[self.data_address])

            logging.debug('output: %s << %s', repr(
                ''.join(self.output_buffer)), repr(symbol))
            self.output_buffer.append(symbol)

    def write(self, sel):
        """ Записать в текущую ячейку или что-то из AC или прочитать символ из стандартного ввода
            sel = true чтение из input
            sel = false чтение из AC """
        if sel:
            if len(self.input_buffer) == 0:
                raise EOFError()
            symbol = self.input_buffer.pop(0)
            symbol_code = ord(symbol)
            assert -128 <= symbol_code <= 127, \
                "input token is out of bound: {}".format(symbol_code)
            self.data_memory[self.data_address] = symbol_code
            logging.debug('input: %s', repr(symbol))
            assert self.min_data_value <= self.data_memory[self.data_address] <= self.max_data_value, \
                f"acc value is out of bound: {self.data_memory[self.data_address]}"

        else:
            self.data_memory[self.data_address] = self.acc
            if isinstance(self.acc, int):
                assert self.min_data_value <= self.data_memory[self.data_address] <= self.max_data_value, \
                    f"acc value is out of bound: {self.data_memory[self.data_address]}"

    def zero(self):
        """ Флаг нужен для циклов и джампов """
        return self.acc == 0

    def neg(self):
        """ Флаг нужен для проверки на знак """
        return self.acc < 0


class ControlUnit():

    def __init__(self, program, data_path):
        self.program = program
        self.program_counter = 0
        self.data_path = data_path
        self._tick = 0
        self.current_arg = 0

    def tick(self):
        """ Счётчик тактов процессора. Вызывается при переходе на следующий такт. """
        self._tick += 1

    def get_current_arg(self):
        return self.current_arg

    def current_tick(self):
        return self._tick

    def latch_program_counter(self, sel_next):
        self.tick()
        if sel_next:
            self.program_counter += 1
        else:
            instr = self.program[self.program_counter]
            assert 'arg' in instr, "internal error"
            self.program_counter = instr["arg"][0]

    def decode_and_execute_instruction(self):
        instr = self.program[self.program_counter]
        opcode = instr["opcode"]

        match opcode:
            case isa_const.Opcode.HALT:
                raise StopIteration()
            case isa_const.Opcode.DEFVAR | isa_const.Opcode.SETQ:
                self.execute_defvar(instr, opcode)
            case isa_const.Opcode.MOD:
                self.execute_mod(instr, opcode)
            case isa_const.Opcode.EQ:
                self.execute_eq()
            case isa_const.Opcode.PLUS | isa_const.Opcode.MINUS | isa_const.Opcode.MUL | isa_const.Opcode.DIV:
                self.execute_alu(instr, opcode)
            case isa_const.Opcode.SETQ:
                self.execute_setq(instr)
            case isa_const.Opcode.JP:
                self.execute_jp()
            case isa_const.Opcode.DOTIMES:
                self.execute_dotimes(instr)
            case isa_const.Opcode.PRINT | isa_const.Opcode.FORMAT:
                self.execute_print(instr, opcode)
            case isa_const.Opcode.READ:
                self.execute_read(instr)

    def execute_defvar(self, instr, opcode):
        self.data_path.latch_data_addr(instr["arg"][0])
        if opcode == isa_const.Opcode.DEFVAR:
            if isinstance(instr["arg"][1], str):
                self.data_path.latch_dr(1, ord(instr["arg"][1]), None)
            else:
                self.data_path.latch_dr(1, instr["arg"][1], None)
            self.tick()
            self.data_path.latch_acc(opcode)

        self.tick()
        self.data_path.write(False)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def execute_mod(self, instr, opcode):
        self.data_path.latch_data_addr(instr["arg"][0])
        self.data_path.latch_dr(1, instr["arg"][1], None)
        self.tick()

        self.data_path.latch_acc(opcode)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def execute_eq(self):
        self.tick()
        if self.data_path.zero():
            self.latch_program_counter(sel_next=True)
        else:
            self.latch_program_counter(sel_next=False)

    def execute_alu(self, instr, opcode):
        self.data_path.latch_data_addr(instr["arg"][0])
        self.tick()

        self.data_path.latch_dr(2, None, None)
        self.tick()
        self.data_path.latch_data_addr(instr["arg"][1])
        self.tick()
        arg_counter = 2
        while arg_counter < len(instr["arg"]):
            self.data_path.latch_dr(3, None, opcode)
            self.tick()
            self.data_path.latch_data_addr(instr["arg"][arg_counter])
            arg_counter += 1
            self.tick()

        self.data_path.latch_acc(opcode)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def execute_setq(self, instr):
        self.data_path.latch_data_addr(instr["arg"])
        self.tick()

        self.data_path.write(False)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def execute_jp(self):
        self.latch_program_counter(sel_next=False)
        self.tick()

    def execute_dotimes(self, instr):
        self.data_path.latch_data_addr(instr["arg"][2])
        self.tick()

        self.data_path.latch_dr(2, None, None)
        self.tick()

        self.data_path.latch_data_addr(instr["arg"][1])
        self.tick()

        self.data_path.latch_acc(isa_const.Opcode.MINUS)
        self.tick()

        if self.data_path.neg() | self.data_path.zero():
            self.latch_program_counter(sel_next=True)
            self.tick()
        else:
            self.data_path.latch_acc("inc")
            self.data_path.latch_data_addr(instr["arg"][2])
            self.tick()

            self.data_path.write(False)
            self.latch_program_counter(sel_next=False)
            self.tick()

    def execute_print(self, instr, opcode):
        self.data_path.latch_data_addr(instr["arg"][0])
        self.tick()
        if opcode == isa_const.Opcode.PRINT.value:
            self.data_path.output(True)
        else:
            self.data_path.output(False)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def execute_read(self, instr):
        self.data_path.latch_data_addr(instr["arg"][0])
        self.tick()
        self.data_path.write(True)
        self.tick()
        self.latch_program_counter(sel_next=True)

    def __repr__(self):
        state = "{{TICK: {}, PC: {}, ADDR: {}, OUT: {}, AC: {}, DR: {}}}".format(
            self._tick,
            self.program_counter,
            self.data_path.data_address,
            self.data_path.data_memory[self.data_path.data_address],
            self.data_path.acc,
            self.data_path.dr,
        )

        instr = self.program[self.program_counter]
        opcode = instr["opcode"]
        arg = instr.get("arg", "")
        action = f"{opcode} {arg}"

        return f"{state} {action}"


def simulation(code, input_tokens, data_memory_size, limit):
    """Запуск симуляции процессора.
    Длительность моделирования ограничена количеством выполненных инструкций.
    """
    data_path = DataPath(data_memory_size, input_tokens)
    control_unit = ControlUnit(code, data_path)
    instr_counter = 0

    logging.debug('%s', control_unit)

    try:
        while True:
            assert limit > instr_counter, "too long execution, increase limit!"
            control_unit.decode_and_execute_instruction()
            instr_counter += 1
            logging.debug('%s', control_unit)
    except EOFError:
        logging.warning('Input buffer is empty!')
    except StopIteration:
        pass
    logging.info('output_buffer: %s', repr(''.join(data_path.output_buffer)))
    return ''.join(data_path.output_buffer), instr_counter, control_unit.current_tick()


def main(args):
    assert len(args) == 2, "Wrong arguments: machine.py <code_file> <input_file>"
    code_file, input_file = args

    code = isa.read_code(code_file)
    with open(input_file, encoding="utf-8") as file:
        input_text = file.read()
        input_token = []
        for char in input_text:
            input_token.append(char)
    output, instr_counter, ticks = simulation(code,
                                              input_tokens=input_token,
                                              data_memory_size=100, limit=100000)

    print(''.join(output))
    print("instr_counter: ", instr_counter, "ticks:", ticks)


if __name__ == '__main__':
    logging.getLogger().setLevel(logging.DEBUG)
    main(sys.argv[1:])
