package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Clear implements Serializable, Command {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException{
        collection.clear();
        s+=("Коллекция успешно очищенна!");
        Send.send(s);
    }
}
