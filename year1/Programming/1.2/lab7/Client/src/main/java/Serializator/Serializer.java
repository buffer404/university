package Serializator;

import Command.Msg;

import java.io.*;
import java.nio.ByteBuffer;

public class Serializer {
    public static byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    public static Msg deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                Msg msg = (Msg) o.readObject();
                msg.strat(null,null);
                return msg;
            }
        }
    }
}
