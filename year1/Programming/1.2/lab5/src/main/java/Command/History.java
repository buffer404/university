package Command;

import Org.Organization;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Класс, используемый для вывода истории последних команд
 */
public class History {

    private final Queue<String> history;

    /**
     * @param a Массив из последних комманд
     */
    public History(Queue<String> a) {
        history = a;
    }

    public void Show_history() {
        for (String i : history) {
            System.out.println(i);
        }
    }

}
