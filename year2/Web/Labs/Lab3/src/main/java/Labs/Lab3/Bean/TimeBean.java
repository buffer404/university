package Labs.Lab3.Bean;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("TimeBean")
public class TimeBean implements Serializable {
    private static final long serialVersionUID = 1;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
    private String time = LocalDateTime.now().format(formatter);;

    public String getTime() {
        return time;
    }

    public void set() {
        System.out.println(1);
        time = LocalDateTime.now().format(formatter);
    }
    private  int number;

    public void increment(){
        number++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
