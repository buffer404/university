package Serializator;

import Collection.Organization;
import Collection.OrganizationId;
import Command.Show;
import Command.ShowAll;
import Controller.ControllerMain;
import NET.Receive;
import NET.Send;
import com.sun.javafx.robot.FXRobot;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class Animation implements Runnable{

    ArrayDeque<Organization> arrayDeque;
    Button animation;
    ScheduledExecutorService service;
    AnchorPane outputanimatio;
    ControllerMain controllerMain;

    public Animation(ArrayDeque<Organization> arrayDeque, Button animation, ScheduledExecutorService service, AnchorPane outputanimation, ControllerMain controllerMain){
        this.arrayDeque=arrayDeque;
        this.animation=animation;
        this.service=service;
        this.outputanimatio=outputanimation;
        this.controllerMain = controllerMain;
    }

    @Override
    public void run() {
        ShowAll show = new ShowAll();
        try {
            Send.send(show);
            if(!compare(arrayDeque, Receive.arrayDeque)){
                service.shutdown();
//                animation.fireEvent(new MouseEvent(MouseEvent.MOUSE_PRESSED, animation.getLayoutX(), animation.getLayoutY(), animation.getLayoutX(), animation.getLayoutY(), MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
                controllerMain.animate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    boolean compare(ArrayDeque<Organization> o1,  ArrayDeque<Organization> o2){
        if(o1.size()==o2.size()){
            ArrayList<Organization> x = new ArrayList<>();
            ArrayList<Organization> y= new ArrayList<>();
            ArrayDeque<Organization> o3 = new ArrayDeque<>(o1);
            for (int i = 0; i < o1.size(); i++) {
                x.add(o3.getFirst());
                y.add(o2.getFirst());
                o2.removeFirst();
                o3.removeFirst();
            }
            for (int i = 0; i < x.size(); i++) {
                if(x.get(i).compareTo(y.get(i))!=0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
