package Command;
import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Show implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;
    String s="";
    ArrayDeque<Organization> organizations;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException {
        synchronized (s){
            organizations=collection;
            System.out.println(organizations);
            Send.send(new Msg(organizations));
        }
    }
}
