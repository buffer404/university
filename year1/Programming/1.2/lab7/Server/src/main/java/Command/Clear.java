package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;
import SQLCommand.ClearCollection;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class Clear implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException{
        collection.clear();
        try {
            ClearCollection.clearCollection(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        s+=("Коллекция успешно очищенна!");
        Send.send(new Msg(s));
    }
}
