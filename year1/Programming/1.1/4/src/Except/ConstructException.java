package Except;

public class ConstructException extends NullPointerException{

    public ConstructException() {
        super("Пожалуйста, введите корректное имя и место");
    }
}
