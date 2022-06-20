package Check;

import Collection.OrganizationType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.NoSuchElementException;

public class Checkbool {

    public static boolean checkInt(String s){
        try {
            Integer i = Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException exception){
            return false;
        }
    }
    public static boolean checkString(String s){
        if (s.trim().equals("")){
            return false;
        }
        return true;
    }
    public static boolean checkDouble(String s){
        try {
            Double i = Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException exception){
            return false;
        }
    }

    public static OrganizationType CheckType(String s){
            if (s.equals("государственная")) {
                return OrganizationType.GOVERNMENT;
            } else if (s.equals("коммерческая")) {
                return OrganizationType.COMMERCIAL;
            } else if (s.equals("частная")) {
                return OrganizationType.PRIVATE_LIMITED_COMPANY;
            } else {
                return OrganizationType.TRUST;
            }
        }

    public static boolean CheckTypeBool(String s) {
            if (s.equals("государственная")) {
                return true;
            } else if (s.equals("коммерческая")) {
                return true;
            } else if (s.equals("частная")) {
                return true;
            } else if (s.equals("трастовая")) {
                return true;
            } else {
                return false;
        }
    }

}
