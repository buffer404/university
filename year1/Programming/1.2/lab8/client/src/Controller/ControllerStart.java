package Controller;

import Check.CheckData;
import Language.Lang;
import NET.Init;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ControllerStart {

    public Label enter_port;

    @FXML
    private Label info;

    @FXML
    private ChoiceBox<String> lang;

    public static String[] language = {"Русский", "Slovenský", "Shqiptare", "Canadian"};

    @FXML
    private TextField port_data;

    @FXML
    private Button connect_button;

    @FXML
    void initialize() {

        lang.getItems().addAll(language);

        lang.setOnAction(event -> {
            switch (lang.getValue()) {
                case "Shqiptare":
                    Lang.locale = new Locale("sq", "AL");
                    connect_button.setText(Lang.getResourceBundle().getString("StartEnter"));
                    enter_port.setText(Lang.getResourceBundle().getString("StartEnterPort"));
                    break;
                case "Русский":
                    Lang.locale = new Locale("", "");
                    connect_button.setText(Lang.getResourceBundle().getString("StartEnter"));
                    enter_port.setText(Lang.getResourceBundle().getString("StartEnterPort"));
                    break;
                case "Slovenský":
                    Lang.locale = new Locale("si", "SI");
                    connect_button.setText(Lang.getResourceBundle().getString("StartEnter"));
                    enter_port.setText(Lang.getResourceBundle().getString("StartEnterPort"));
                    break;
                default:
                    Lang.locale = new Locale("en", "CA");
                    connect_button.setText(Lang.getResourceBundle().getString("StartEnter"));
                    enter_port.setText(Lang.getResourceBundle().getString("StartEnterPort"));
                    break;
            }
        });

        String port = "12345";
        if (CheckData.CheckInteger(port) == -1) {
            info.setText(Lang.getResourceBundle().getString("wrongData"));
        } else {
            int ports = 12345;
            System.out.println("Пытаюсь подключиться к серверу");
            if (!Init.init(ports)) {
                info.setText(Lang.getResourceBundle().getString("wrongData"));
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../FXML/sample.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 1000, 700));
                stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));

                stage.show();
            }
        }



    }
}
