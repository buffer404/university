package Command;

import Check.CheckData;
import Collection.*;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Add implements Serializable, Command {
    String s;
    Organization organization;
    public Add(String name,Coordinates coordinates, double annualTurnover, String fullName, long employeesCount, OrganizationType organizationType,Address postalAddress){
        this.organization = new Organization(name, coordinates, annualTurnover, fullName, employeesCount, organizationType, postalAddress);
    }
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException {
        collection.add(organization);
        s+=("Организация "+organization.getFullName()+" успешно добавлена");
        Send.send(s);
    }
}
