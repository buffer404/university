package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/Start.fxml"));
        primaryStage.setTitle("Org.inc");
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));
        primaryStage.setScene(new Scene(root, 1000, 700));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
