package Controller;

import Language.Lang;
import NET.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ControllerRegister {

    @FXML
    private TextField user_data;
    @FXML
    private Label info;
    @FXML
    private Button register_button;

    @FXML
    private ChoiceBox<String> lang;

    @FXML
    private PasswordField password_data;

    @FXML
    void initialize() {

        register_button.setOnMouseClicked(event -> {
            if (user_data.getText().trim().equals("") || password_data.getText().trim().equals("")){
                info.setText(Lang.getResourceBundle().getString("wrongData"));
            }
            else {
                Login.user=user_data.getText();
                Login.password=user_data.getText();
                if(Login.login(false)){
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
                    register_button.getScene().getWindow().hide();
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
            user_data.setPromptText(Lang.getResourceBundle().getString("sampleUser"));
            password_data.setPromptText(Lang.getResourceBundle().getString("samplePassword"));
            register_button.setText(Lang.getResourceBundle().getString("RegisterRegister"));
        });

    }
}