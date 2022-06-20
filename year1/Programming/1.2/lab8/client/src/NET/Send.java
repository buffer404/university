package NET;

import Serializator.Serializer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Send {

    public static Object send(Object o) throws IOException, ClassNotFoundException {
try{
    DatagramChannel check = DatagramChannel.open().bind(new InetSocketAddress(Init.ServerIP, Init.ServerPort));
    check.close();
    System.out.println("Cервер не доступен");
    Stage primaryStage = new Stage();
    primaryStage.setWidth(300);
    primaryStage.setHeight(200);

    Label helloWorldLabel = new Label("Произошла ошибка на сервере. Пожалуйста зайдите позже(");
    helloWorldLabel.setWrapText(true);
    helloWorldLabel.setAlignment(Pos.CENTER);
    Scene primaryScene = new Scene(helloWorldLabel);
    primaryStage.setScene(primaryScene);
    primaryStage.show();
    }
        catch (IOException e){

            ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(o));
            Init.Client.send(buffer, new InetSocketAddress(Init.ServerIP, Init.ServerPort));
            return Receive.receive();

        }
        return null;
    }
}
