package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Info implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;
    String s="";
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        s+=collection.getClass();
        s+="\n";
        s+="Количество организаций ";
        s+=collection.size();
        Send.send(new Msg(s));
    }
}
