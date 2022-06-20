package Collection;

import java.io.Serializable;

/**
 * Класс адресс, используется при создании организации
 */
public class Address implements Serializable {
    /**
     * Почтовый адресс
     */
    private String zipCode; //Длина строки должна быть не меньше 6, Поле может быть null
    /**
     * Город
     */
    private Location town; //Поле не может быть null

    public Address(String zipCode, Location town){
        this.zipCode=zipCode;
        this.town=town;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", town=" + town +
                '}';
    }
}
