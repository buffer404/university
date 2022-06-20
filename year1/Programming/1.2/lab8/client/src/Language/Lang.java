package Language;
import java.util.Locale;
import java.util.ResourceBundle;

public class Lang {
    public static Locale locale = new Locale("", "");

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("Language.resourse", locale);
    }
}
