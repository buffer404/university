package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import Language.Lang;
import NET.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Main;

public class ControllerLogin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label info;

    @FXML
    private Button login_button;

    @FXML
    private TextField user_data;

    @FXML
    private Button register_button;

    @FXML
    private ChoiceBox<String> lang;

    @FXML
    private PasswordField password_data;

    @FXML
    void initialize() {

        lang.getItems().addAll(ControllerStart.language);

        register_button.setOnMouseClicked(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../FXML/Register.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1000,700));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));
            register_button.getScene().getWindow().hide();
            stage.show();
        });

        login_button.setOnMouseClicked(event -> {
            if (user_data.getText().trim().equals("") || password_data.getText().trim().equals("")){
                info.setText(Lang.getResourceBundle().getString("wrongData"));
            }
            else {
                Login.user=user_data.getText();
                Login.password=user_data.getText();
                if(Login.login(true)){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../FXML/main.fxml"));
                    try {
                        fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 1341,700));
                    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));
                    login_button.getScene().getWindow().hide();
                    stage.show();
                }
                else {
                    info.setText(Lang.getResourceBundle().getString("wrongData"));
                }
            }
        });

        lang.setOnAction(event -> {
            if(lang.getValue().equals("Shqiptare")){
                Lang.locale=new Locale("sq","AL");
            }
            else if(lang.getValue().equals("Русский")){
                Lang.locale=new Locale("","");
            }
            else if(lang.getValue().equals("Slovenský")){
                Lang.locale=new Locale("si","SI");
            }
            else{
                Lang.locale=new Locale("en","CA");
            }
            register_button.setText(Lang.getResourceBundle().getString("sampleRegistration"));
            user_data.setPromptText(Lang.getResourceBundle().getString("sampleUser"));
            password_data.setPromptText(Lang.getResourceBundle().getString("samplePassword"));
            login_button.setText(Lang.getResourceBundle().getString("sampleLogin"));
        });

    }
}
