package Command;

import Collection.Organization;
import Interface.Command;
import NET.Login;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Log implements Serializable, Command {
    private static final long serialVersionUID = 1234567L;
    public String u;
    public String password;
    public boolean k;
    public static boolean i=false;

    public Log(String user, String password, boolean k) {
        this.u = user;
        this.password = password;
        this.k = k;
    }

    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        if(Login.login(u,password,k)){
            Send.send(new Msg(user+", добро пожаловать"));
        }
        else {
            Send.send(new Msg("Некорректно введены данные"));
        }

    }
}

