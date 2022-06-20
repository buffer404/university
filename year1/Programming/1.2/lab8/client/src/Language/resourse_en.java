package Language;

import java.util.ListResourceBundle;

public class resourse_en extends ListResourceBundle {

    public static final Object[][] content = {
            {"StartPort", "port"},
            {"StartEnter","enter"},
            {"StartEnterPort", "Enter port"},
            {"sampleUser","user"},
            {"samplePassword","password"},
            {"sampleLogin","login"},
            {"sampleRegistration","registration"},
            {"wrongData","Incorrect data entered!"},
            {"RegisterUser","user"},
            {"RegisterPassword","password"},
            {"RegisterRegister","registration"},

            {"help","Help"},
            {"info", "Info"},
            {"history", "History"},
            {"middleprice", "Annual Price"},
            {"price", "Price"},
            {"fullName", "Full name"},
            {"Org", "My organizations"},
            {"Allorg", "All organizations"},
            {"animation", "Animation"},
            {"result", "Result"},
            {"logout", "Log off"},

            {"Name","Name(int)"},
            {"Coordinates","Coordinates"},
            {"Annual Turnover","Annual Turnover"},
            {"Full name","Full name"},
            {"Employee Count","Employee Count"},
            {"Type","Type"},
            {"Postal address","Postal address"},
            {"Id","ID"},
            {"Delete","Delete"},
            {"Edit","Edit"},
            {"Date", "Date"},
    };

    @Override
    public Object[][] getContents() {
        return content;
    }
}
