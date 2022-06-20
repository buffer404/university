package Command;
import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Show implements Command, Serializable {

    String s="";
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        for (Organization i : collection){
            s+=i.toString();
        }
        Send.send(new Msg(s));
    }
}
