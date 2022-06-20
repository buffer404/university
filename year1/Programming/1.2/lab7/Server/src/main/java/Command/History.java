package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

public class History implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;
    String s;
    public Queue<String> data;
    public History(Queue<String> q){
        data=q;
    }
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException{
        s+=data.toString();
        Send.send(new Msg(s));
    }
}
