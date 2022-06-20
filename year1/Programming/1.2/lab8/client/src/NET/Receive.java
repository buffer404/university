package NET;

import Collection.Organization;
import Collection.OrganizationId;
import Command.Msg;
import Serializator.Serializer;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;


public class Receive {
    public static String info="";
    public static ArrayDeque<Organization> arrayDeque;
    public static ArrayDeque<OrganizationId> organizationIds;
    public static Msg receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        SocketAddress remoteAdd = Init.Client.receive(buffer);
        Msg msg = Serializer.deserialize(buffer.array());
        try {
            msg.owners.getFirst();
            organizationIds=msg.owners;
        }catch (NullPointerException e){
        }
        try {
            msg.organizations.getFirst();
            arrayDeque=msg.organizations;
        }catch (NullPointerException e){
        }
        info=msg.sms;
        return msg;
    }
}
