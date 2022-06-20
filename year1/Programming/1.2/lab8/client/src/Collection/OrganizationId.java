package Collection;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class OrganizationId implements Serializable, Comparable<Organization>{
    private static final long serialVersionUID = 1234567L;

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double annualTurnover; //Значение поля должно быть больше 0
    private String fullName; //Значение этого поля должно быть уникальным, Длина строки не должна быть больше 915, Поле может быть null
    private Long employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null
        private String owner;
    /**
     *
     * @param name Название организации
     * @param coordinates Координаты организации
     * @param annualTurnover Доход организации
     * @param fullName Полное название организации
     * @param employeesCount Количество работников в организации
     * @param organizationType Тип организации
     * @param postalAddress Почтовый адресс
     */
    public OrganizationId(String name,Coordinates coordinates, double annualTurnover, String fullName, long employeesCount, OrganizationType organizationType,Address postalAddress){
        Random random = new Random();
        id = random.nextInt(2147483647);
        this.name = name;
        this.coordinates = coordinates;
        creationDate = new Date();
        this.annualTurnover=annualTurnover;
        this.fullName=fullName;
        this.employeesCount=employeesCount;
        this.type=organizationType;
        this.postalAddress = postalAddress;
    }

    /**
     *
     * @param id ID организации
     * @param name Название организации
     * @param coordinates Координаты организации
     * @param annualTurnover Доход организации
     * @param fullName Полное название организации
     * @param employeesCount Количество работников в организации
     * @param organizationType Тип организации
     * @param postalAddress Почтовый адресс
     */
    public OrganizationId(String owner, Integer id, String name,Coordinates coordinates, double annualTurnover, String fullName, long employeesCount, OrganizationType organizationType,Address postalAddress){
        this.id=id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = new Date();
        this.annualTurnover=annualTurnover;
        this.fullName=fullName;
        this.employeesCount=employeesCount;
        this.type=organizationType;
        this.postalAddress = postalAddress;
        this.owner=owner;
    }

    /**
     * Получение полного имени
     * @return Полное название
     */
    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", fullName='" + fullName + '\'' +
                ", employeesCount=" + employeesCount +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                '}';
    }

    /**
     * Получение ID
     * @return ID
     */
    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    /**
     * Вывод годового дохода организации
     * @return Доход организации
     */
    public double getAnnualTurnover(){
        return annualTurnover;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return (int) (this.getAnnualTurnover()-o.getAnnualTurnover());
    }
}

