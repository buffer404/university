package Command;

import Collection.Organization;
import Interface.Command;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Msg implements Command, Serializable {
    public  boolean i = false;
    private String sms;

    public Msg(boolean i, String sms) {
        this.i = i;
        this.sms = sms;
    }

    public Msg(String sms) {
        this.sms = sms;
    }

    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException {
        System.out.println(sms);
    }
}
