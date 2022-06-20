package Command;

import Collection.Organization;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Hello implements Serializable, Command {
    int port;
    public Hello(int port){
        this.port = port;
    }
    @Override
    public void strat(ArrayDeque<Organization> collection) throws IOException {
        System.out.println("Привет от клиента "+ port);
        Send.send("Привет от сервера");
    }
}
