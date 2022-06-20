package utility;

public enum Position {
    INDEFINETELY("неопределенное"), VERTICALLY("вертикальное"), HORIZONTALLY("горизонтальное");

    private String info;

    Position(String s){
        info = s;
    }

    public String getInfo() {
        return info;
    }
}
