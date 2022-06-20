package utility;

public enum Place {
    EARTH("земли"), MOON("луны");

    private String info;

    Place(String s){
        info = s;
    }

    public String getInfo() {
        return info;
    }
}
