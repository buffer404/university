package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class PrintUniqueFullName implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        for(Organization i : collection){
            s+=(i.getFullName()+"\n");
        }
        Send.send(new Msg(s));

    }
}
