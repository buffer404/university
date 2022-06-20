package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class PrintUniqueFullName implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException {
        String s="";
        for(Organization i : collection){
            s= s + i.getFullName()+"\n";
        }
        Send.send(new Msg(s));

    }
}
