package Command;

import Collection.Organization;
import Collection.OrganizationId;
import Interface.Command;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Msg implements Command, Serializable {
    private static final long serialVersionUID = 1234567L;
    public  boolean i = false;
    public String sms="";
    public ArrayDeque<Organization> organizations;
    public ArrayDeque<OrganizationId> owners;

    public Msg(boolean i, String sms) {
        this.i = i;
        this.sms = sms;
    }

    public Msg(String sms) {
        this.sms = sms;
    }

    public Msg(ArrayDeque<Organization> organizations){
        this.organizations=organizations;
    }

    public Msg(ArrayDeque<OrganizationId> o, String s){
        owners=o;
    }

    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException {
        System.out.println(sms);
    }
}
