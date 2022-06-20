package Command;

import Collection.Organization;
import Interface.ClearCollection;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Clear implements Serializable, Command {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException{
        collection.clear();
            ClearCollection.clearCollection(user);
        s+=("Коллекция успешно очищенна!");
        try {
            Send.send(new Msg(s));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
