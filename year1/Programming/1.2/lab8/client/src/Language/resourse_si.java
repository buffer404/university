package Language;

import java.util.ListResourceBundle;

public class resourse_si extends ListResourceBundle {

    public static final Object[][] content = {
            {"StartPort", "pristanišče"},
            {"StartEnter","vnesite"},
            {"StartEnterPort", "vstopite v pristanišče"},
            {"sampleUser","uporabnik"},
            {"samplePassword","geslo"},
            {"sampleLogin","Vpiši se"},
            {"sampleRegistration","registrirati"},
            {"wrongData","Vneseni netočni podatki!"},
            {"RegisterUser","uporabnik"},
            {"RegisterPassword","geslo"},
            {"RegisterRegister","registrirati"},

            {"help","Pomoč"},
            {"info", "INFA"},
            {"history", "Zgodovina"},
            {"middleprice", "Povprečni dohodek"},
            {"price", "dohodek"},
            {"fullName", "Celoten naslov"},
            {"Org", "Moje organizacije "},
            {"Allorg", "Vse organizacije"},
            {"animation", "Animacija"},
            {"result", "Rezultat"},
            {"logout", "Log off"},

            {"Name","Ime"},
            {"Coordinates","Koordinate"},
            {"Annual Turnover","Letni promet"},
            {"Full name","Polno ime"},
            {"Employee Count","Število zaposlenih"},
            {"Type","Vrsta"           },
            {"Postal address","poštni naslov"},
            {"Id","Id"},
            {"Delete","Izbriši"},
            {"Edit","Uredi"},
            {"Date", "Datum"}

    };

    @Override
    public Object[][] getContents() {
        return content;
    }
}
