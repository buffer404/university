package Main;

import Check.CheckData;
import Collection.Organization;
import Command.*;
import Interface.Command;
import NET.Receive;
import NET.Socket;
import Pharser.Pharse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Work {
    public static ArrayDeque<Organization> collection;

    public void start() throws IOException, ClassNotFoundException {


        try {
            System.out.println("Введите порт");
            Socket socket = new Socket(CheckData.CheckInt());
            collection = Pharse.read();
            while (true){
                Object o = Receive.receive();
                Command command = (Command) o;
                command.strat(collection);
            }
        }
        catch (Exception e){

        }

    }
}
