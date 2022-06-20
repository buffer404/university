package Command;
import Collection.Organization;
import Collection.OrganizationId;
import Interface.Command;
import NET.Send;
import SQLCommand.LoadCollection;
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
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException, SQLException {
        ArrayDeque<OrganizationId> a = LoadCollection.loadowner();
        System.out.println(a);
        Send.send(new Msg(a,s));
    }
    
}