package SQLCommand;

import Collection.*;
import Command.Msg;
import NET.DBHandler;
import NET.Send;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Date;

public class LoadCollection {
    public final static String LOAD_COLLECTION_FOR = "SELECT * FROM org WHERE owner =?";
    public final static String LOAD_COLLECTION = "SELECT * FROM org";
    public static ArrayDeque<Organization> loadCollection(String user) throws SQLException {
        ArrayDeque<Organization> arrayDeque = new ArrayDeque();
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(LOAD_COLLECTION_FOR);
        addStatement.setString(1, user);
        ResultSet resultSet = addStatement.executeQuery();
        while (resultSet.next()) {
            Organization o = makeOrganization(resultSet);
            arrayDeque.add(o);
        }
        addStatement.close();
        System.out.println("Список организаций успешно загружен!!!");
        return arrayDeque;
    }

    public static ArrayDeque<Organization> loadCollection() throws SQLException {
        ArrayDeque<Organization> arrayDeque = new ArrayDeque();
        ResultSet resultSet=null;
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(LOAD_COLLECTION);
        try {
            resultSet = addStatement.executeQuery();
        }
        catch (PSQLException e){
            System.err.println("Произошел сбой в базе данных");
            try {
                Send.send(new Msg("Произошел сбой в базе данных"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);
        }

        while (resultSet.next()) {
            Organization o = makeOrganization(resultSet);
            arrayDeque.add(o);
        }
        addStatement.close();
        System.out.println("Список организаций успешно загружен");
        return arrayDeque;
    }

    public static ArrayDeque<OrganizationId> loadowner() throws SQLException {
        ArrayDeque<OrganizationId> arrayDeque = new ArrayDeque();
        ResultSet resultSet=null;
        PreparedStatement addStatement = DBHandler.connection.prepareStatement(LOAD_COLLECTION);
        try {
            resultSet = addStatement.executeQuery();
        }
        catch (PSQLException e){
            System.err.println("Произошел сбой в базе данных");
            try {
                Send.send(new Msg("Произошел сбой в базе данных"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);
        }
        while (resultSet.next()) {
            OrganizationId o = makeOrganizationOwner(resultSet);
            arrayDeque.add(o);
        }
        addStatement.close();
        System.out.println("Список организаций успешно загружен");
        return arrayDeque;
    }

    public static Organization makeOrganization(ResultSet rs) {
        try {
            int id = rs.getInt("idorg");
            String name = rs.getString("name");
            int x = rs.getInt("coordx");
            int y = rs.getInt("coordy");
            Date date = rs.getDate("date");
            double AnnualTurnover = rs.getDouble("annualturnover");
            String fullname = rs.getString("fullname");
            Long employ = rs.getLong("employeescount");
            OrganizationType op = OrganizationType.valueOf(rs.getString("type"));
            String zipcode = rs.getString("zipcode");
            double lx = rs.getDouble("locationx");
            int ly = rs.getInt("locationy");
            int lz = rs.getInt("locationz");
            String locationName = rs.getString("town");
            String owner = rs.getString("owner");

            Location location = new Location(lx, ly, lz, locationName);
            Address address = new Address(zipcode, location);
            Coordinates coordinates = new Coordinates(x, y);

            Organization o = new Organization(id, name, coordinates, AnnualTurnover, fullname, employ, op, address);
            return o;

        } catch (SQLException throwables) {
            System.err.println("Ошибка при считывании значения из БД");
            throwables.printStackTrace();
            return null;
        }


    }
    public static OrganizationId makeOrganizationOwner(ResultSet rs) {
        try {
            int id = rs.getInt("idorg");
            String name = rs.getString("name");
            int x = rs.getInt("coordx");
            int y = rs.getInt("coordy");
            Date date = rs.getDate("date");
            double AnnualTurnover = rs.getDouble("annualturnover");
            String fullname = rs.getString("fullname");
            Long employ = rs.getLong("employeescount");
            OrganizationType op = OrganizationType.valueOf(rs.getString("type"));
            String zipcode = rs.getString("zipcode");
            double lx = rs.getDouble("locationx");
            int ly = rs.getInt("locationy");
            int lz = rs.getInt("locationz");
            String locationName = rs.getString("town");
            String owner = rs.getString("owner");

            Location location = new Location(lx, ly, lz, locationName);
            Address address = new Address(zipcode, location);
            Coordinates coordinates = new Coordinates(x, y);

            OrganizationId o = new OrganizationId(owner, id, name, coordinates, AnnualTurnover, fullname, employ, op, address);
            return o;

        } catch (SQLException throwables) {
            System.err.println("Ошибка при считывании значения из БД");
            throwables.printStackTrace();
            return null;
        }


    }
}

