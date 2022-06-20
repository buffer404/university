package Except;

import java.util.Scanner;

/**
 * Класс, который используется при повторном вводе данных
 */

public class IncorrectInput {
    /**
     * Ввод новой строки
     * @param scanner Класс, который определяет как мы будем считывать информацию
     * @return Новая строка
     */
    public String StringInfo (Scanner scanner){
        System.err.println("Некорректный ввод данных, пожалуйста введите данные согласно запросу!");
        return scanner.nextLine();
    }
}
