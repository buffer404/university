package Command;
import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class ShowAll implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;

    String s="";
    public ArrayDeque<Organization> organizations;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        organizations=collection;
        for (Organization i : collection){
            s+=i.toString();
        }
        Send.send(new Msg(s));
    }
}