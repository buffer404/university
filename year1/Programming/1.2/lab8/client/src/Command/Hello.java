package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Hello implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    int port;
    public Hello(int port){
        this.port = port;
    }
    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        System.out.println("Привет от клиента");
        Send.send(new Msg("Привет от сервера"));
    }
}
