package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class PrintUniqueFullName implements Serializable, Command {
    String s;
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException {
        for(Organization i : collection){
            s+=(i.getFullName()+"\n");
        }
        Send.send(s);

    }
}
