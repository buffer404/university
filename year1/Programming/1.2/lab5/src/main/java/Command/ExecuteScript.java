package Command;

import start.Init;
import start.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс, используемый при запуске скрипта
 */
public class ExecuteScript {
    /**
     * @param path Путь до скрипта
     */

    private final String path;

    public ExecuteScript(String path) {
        this.path = path;
    }

    public void Start() {
        File script = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(script);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        }
        System.out.println("Начинаю выполнение скрипта");
        Init init = new Init(scanner);
        init.init_go();
    }
}
