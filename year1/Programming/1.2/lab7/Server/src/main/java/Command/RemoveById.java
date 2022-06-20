package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;
import SQLCommand.ClearCollection;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class RemoveById implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    String s;
    int id;
    public RemoveById(int id){
        this.id=id;
    }

    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException, SQLException {
        ClearCollection.clearId(id);
        Send.send(new Msg(s));
    }
}
