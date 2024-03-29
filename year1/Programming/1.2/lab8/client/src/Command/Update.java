package Command;

import Collection.Address;
import Collection.Coordinates;
import Collection.Organization;
import Collection.OrganizationType;
import Interface.CheckUpdate;
import Interface.Command;
import Interface.DBHandler;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class Update implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    String s="";
    Organization organization;
    public Update(int id, String name, Coordinates coordinates, double annualTurnover, String fullName, long employeesCount, OrganizationType organizationType, Address postalAddress){
        this.organization = new Organization(id, name, coordinates, annualTurnover, fullName, employeesCount, organizationType, postalAddress);
    }
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {

        collection.add(organization);

        String ADD_ORGANIZATION = "INSERT INTO org (IDORG, NAME, COORDX, COORDY, DATE, ANNUALTURNOVER, FULLNAME, EMPLOYEESCOUNT, TYPE, ZIPCODE, LOCATIONX, LOCATIONY, LOCATIONZ, TOWN, OWNER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DBHandler.connection.prepareStatement(ADD_ORGANIZATION);
            ps.setInt(1, organization.getId());
            ps.setString(2, organization.getName());
            ps.setInt(3, organization.getCoordinates().getX());
            ps.setInt(4, organization.getCoordinates().getY());
            ps.setDate(5, new java.sql.Date(organization.getCreationDate().getTime()));
            ps.setDouble(6, organization.getAnnualTurnover());
            ps.setString(7, organization.getFullName());
            ps.setInt(8, organization.getEmployeesCount().intValue());
            ps.setString(9, String.valueOf(organization.getType()));
            ps.setString(10, organization.getPostalAddress().getZipCode());
            ps.setDouble(11, organization.getPostalAddress().getTown().getX());
            ps.setInt(12, organization.getPostalAddress().getTown().getY());
            ps.setInt(13, organization.getPostalAddress().getTown().getZ());
            ps.setString(14, organization.getPostalAddress().getTown().getName());
            ps.setString(15, user);

            if (CheckUpdate.checkUpdate(user, organization.getId())){
                ps.executeUpdate();
                ps.close();
                s+=("Организация "+organization.getFullName()+" успешно обновлена");
                Send.send(new Msg(s));
            }
            else {
                s+=("Вы не можете управлять данной оргпнизацией");
                Send.send(new Msg(s));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

