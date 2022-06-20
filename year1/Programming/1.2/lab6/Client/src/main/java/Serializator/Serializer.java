package Serializator;

import sun.misc.IOUtils;

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

    public static void deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String msg = new String(bytes, "UTF8");
        System.out.println(msg);
    }
}
