package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Info implements Command, Serializable {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException {
        s+=collection.getClass();
        s+="\n";
        s+=collection.size();
        Send.send(s);
    }
}
