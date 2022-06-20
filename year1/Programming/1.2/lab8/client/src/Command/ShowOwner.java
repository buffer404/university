package Command;
import Collection.Organization;
import Collection.OrganizationId;
import Interface.Command;
import Interface.LoadCollection;
import NET.Send;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class ShowOwner implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;

    String s="";
    public ArrayDeque<OrganizationId> organizations;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        ArrayDeque<OrganizationId> a = LoadCollection.loadowner();
        for (OrganizationId i : a){
            organizations.add(new OrganizationId(i.getOwner(), i.getId(), i.getName(), i.getCoordinates(), i.getAnnualTurnover(), i.getFullName(), i.getEmployeesCount(), i.getType(), i.getPostalAddress()));

        }
        Send.send(new Msg(s));
    }

}