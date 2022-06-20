package utility;

public enum Weight {
    LOSEWEIGHT("теряют вес"), HAVEWEIGHT("имеют вес"), NOWEIGHT("без веса");

    private String AboutWeight;

    Weight(String s){
        AboutWeight = s;
    }

    public String getAboutWeight() {
        return AboutWeight;
    }
}
