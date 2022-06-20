package Threads;

import Collection.Organization;
import Interface.Command;
import Main.Work;
import SQLCommand.LoadCollection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class ReceiveThread extends Thread{
    private Command command;
    private String user = "";
    private ArrayDeque arrayDeque;
    public ReceiveThread(Command command, String user, ArrayDeque<Organization> arrayDeque){
        this.arrayDeque=arrayDeque;
        this.command=command;
        this.user=user;
    }
    @Override
    public void run() {
        try {
            command.strat(arrayDeque,user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            try {
                ArrayDeque ar = LoadCollection.loadCollection();
                command.strat(ar,user);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
