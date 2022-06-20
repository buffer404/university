package Controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Check.CheckData;
import Check.Checkbool;
import Collection.*;
import Command.*;
import Language.Lang;
import NET.Receive;
import NET.Send;
import Serializator.Animation;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ControllerMain {

    @FXML
    public Label username;
    @FXML
    public Button orgAll;
    @FXML
    public Button animation;
    @FXML
    public Button logout;
    @FXML
    private AnchorPane outputanimation;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public static boolean k = false;

    @FXML
    private Button average;

    @FXML
    private Button org;

    @FXML
    private Button countLess;

    @FXML
    private Button fullName;

    @FXML
    private Button history;

    @FXML
    private Button send;

    @FXML
    private Label output;

    @FXML
    private Label result;

    @FXML
    private Button help;

    @FXML
    private Button removeId;


    @FXML
    private Button removeAll;

    @FXML
    private ChoiceBox<String> lang;

    @FXML
    private AnchorPane anime;

    @FXML
    private Label commands;

    public static String s = "";

    public static String user;

    ArrayList<String> historyqueue = new ArrayList<>();


    @FXML
    void initialize() {
        lang.getItems().addAll(ControllerStart.language);
        username.setText(user);

        average.setOnAction(event -> {
            historyqueue.add("Средний доход");
            AverageOfAnnualTurnover averageOfAnnualTurnover = new AverageOfAnnualTurnover();
            try {
                Send.send(averageOfAnnualTurnover);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.setText(Receive.info);
        });

        lang.setOnAction(event -> {
            if (lang.getValue().equals("Shqiptare")) {
                Lang.locale = new Locale("sq", "AL");
            } else if (lang.getValue().equals("Русский")) {
                Lang.locale = new Locale("", "");
            } else if (lang.getValue().equals("Slovenský")) {
                Lang.locale = new Locale("si", "SI");
            } else {
                Lang.locale = new Locale("en", "CA");
            }
            average.setText(Lang.getResourceBundle().getString("middleprice"));
            countLess.setText(Lang.getResourceBundle().getString("price"));
            help.setText(Lang.getResourceBundle().getString("help"));
            history.setText(Lang.getResourceBundle().getString("history"));
            this.fullName.setText(Lang.getResourceBundle().getString("fullName"));
            removeAll.setText(Lang.getResourceBundle().getString("info"));
            animation.setText(Lang.getResourceBundle().getString("animation"));
            org.setText(Lang.getResourceBundle().getString("Org"));
            orgAll.setText(Lang.getResourceBundle().getString("Allorg"));
            result.setText(Lang.getResourceBundle().getString("result"));
            logout.setText(Lang.getResourceBundle().getString("logout"));
        });


        animation.setOnAction(event -> animate());

        countLess.setOnAction(event -> {
            historyqueue.add("Доход");
            CountLessThanAnnualTurnover countLessThanAnnualTurnover = new CountLessThanAnnualTurnover(0);
            try {
                Send.send(countLessThanAnnualTurnover);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.setText(Receive.info);
        });

        history.setOnAction(event -> {
            output.setText(historyqueue.toString());
        });

        help.setOnAction(event -> {
            historyqueue.add("Помощь");
            output.setText(Help.s);
        });

        fullName.setOnAction(event -> {
            historyqueue.add("Полное название");
            PrintUniqueFullName printUniqueFullName = new PrintUniqueFullName();
            try {
                Send.send(printUniqueFullName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.setText(Receive.info);
        });

        removeAll.setOnAction(event -> {
            historyqueue.add("Инфа");
            Info info = new Info();
            try {
                Send.send(info);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.setText(Receive.info);
        });

        orgAll.setOnAction(event -> {
            TableColumn<Organization, String> name = new TableColumn<>(Lang.getResourceBundle().getString("Name"));
            name.setMinWidth(150);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Organization, Integer> id = new TableColumn<>(Lang.getResourceBundle().getString("Id"));
            id.setMinWidth(150);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Organization, Coordinates> coordinates = new TableColumn<>(Lang.getResourceBundle().getString("Coordinates"));
            coordinates.setMinWidth(150);
            coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
            TableColumn<Organization, Date> creationDate = new TableColumn<>(Lang.getResourceBundle().getString("Date"));
            creationDate.setMinWidth(150);
            creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            TableColumn<Organization, Double> annualTurnover = new TableColumn<>(Lang.getResourceBundle().getString("Annual Turnover"));
            annualTurnover.setMinWidth(150);
            annualTurnover.setCellValueFactory(new PropertyValueFactory<>("annualTurnover"));
            TableColumn<Organization, String> fullName = new TableColumn<>(Lang.getResourceBundle().getString("Full name"));
            fullName.setMinWidth(150);
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            TableColumn<Organization, Long> employeesCount = new TableColumn<>(Lang.getResourceBundle().getString("Employee Count"));
            employeesCount.setMinWidth(150);
            employeesCount.setCellValueFactory(new PropertyValueFactory<>("employeesCount"));
            TableColumn<Organization, OrganizationType> type = new TableColumn<>(Lang.getResourceBundle().getString("Type"));
            type.setMinWidth(150);
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            TableColumn<Organization, Address> postalAddress = new TableColumn<>(Lang.getResourceBundle().getString("Postal address"));
            postalAddress.setMinWidth(150);
            postalAddress.setCellValueFactory(new PropertyValueFactory<>("postalAddress"));

            TableView tableView = new TableView();
            tableView.setItems(getAllOrganization());
            tableView.getColumns().addAll(name, id, coordinates, creationDate, annualTurnover, fullName, employeesCount, type, postalAddress);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView);
            Scene scene = new Scene(vBox);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));
            stage.show();

        });

        org.setOnAction(event -> {
            TableColumn<Organization, String> name = new TableColumn<>(Lang.getResourceBundle().getString("Name"));
            name.setMinWidth(150);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Organization, Integer> id = new TableColumn<>(Lang.getResourceBundle().getString("Id"));
            id.setMinWidth(150);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Organization, Coordinates> coordinates = new TableColumn<>(Lang.getResourceBundle().getString("Coordinates"));
            coordinates.setMinWidth(150);
            coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
            TableColumn<Organization, Date> creationDate = new TableColumn<>(Lang.getResourceBundle().getString("Date"));
            creationDate.setMinWidth(150);
            creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            TableColumn<Organization, Double> annualTurnover = new TableColumn<>(Lang.getResourceBundle().getString("Annual Turnover"));
            annualTurnover.setMinWidth(150);
            annualTurnover.setCellValueFactory(new PropertyValueFactory<>("annualTurnover"));
            TableColumn<Organization, String> fullName = new TableColumn<>(Lang.getResourceBundle().getString("Full name"));
            fullName.setMinWidth(150);
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            TableColumn<Organization, Long> employeesCount = new TableColumn<>(Lang.getResourceBundle().getString("Employee Count"));
            employeesCount.setMinWidth(150);
            employeesCount.setCellValueFactory(new PropertyValueFactory<>("employeesCount"));
            TableColumn<Organization, OrganizationType> type = new TableColumn<>(Lang.getResourceBundle().getString("Type"));
            type.setMinWidth(150);
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            TableColumn<Organization, Address> postalAddress = new TableColumn<>(Lang.getResourceBundle().getString("Postal address"));
            postalAddress.setMinWidth(150);
            postalAddress.setCellValueFactory(new PropertyValueFactory<>("postalAddress"));

            TextField Nameinput, Coordinatesinput, Annualinput, FullNameinput, Employeeinput, Postalinput, Idinput;
            Nameinput = new TextField();
            Nameinput.setPromptText(Lang.getResourceBundle().getString("Name"));
            Nameinput.setMaxWidth(125);
            Coordinatesinput = new TextField();
            Coordinatesinput.setPromptText(Lang.getResourceBundle().getString("Coordinates"));
            Coordinatesinput.setMaxWidth(125);
            Annualinput = new TextField();
            Annualinput.setPromptText(Lang.getResourceBundle().getString("Annual Turnover"));
            Annualinput.setMaxWidth(125);
            FullNameinput = new TextField();
            FullNameinput.setPromptText(Lang.getResourceBundle().getString("Full name"));
            FullNameinput.setMaxWidth(125);
            Employeeinput = new TextField();
            Employeeinput.setPromptText(Lang.getResourceBundle().getString("Employee Count"));
            Employeeinput.setMaxWidth(125);
            ChoiceBox<String> Typyinput = new ChoiceBox<>(FXCollections.observableArrayList("частная", "трастовая", "государственная", "коммерческая"));
            Typyinput.setMaxWidth(125);
            Postalinput = new TextField();
            Postalinput.setPromptText(Lang.getResourceBundle().getString("Postal address"));
            Postalinput.setMaxWidth(125);
            Idinput = new TextField();
            Idinput.setPromptText(Lang.getResourceBundle().getString("Id"));
            Idinput.setMaxWidth(125);


            Button addButton = new Button("Add");
            Button deleteButton = new Button(Lang.getResourceBundle().getString("Delete"));
            Button editButton = new Button(Lang.getResourceBundle().getString("Edit"));

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(9, 9, 9, 9));
            hBox.setSpacing(9);
            hBox.getChildren().addAll(Nameinput, Idinput, Coordinatesinput, Annualinput, FullNameinput, Employeeinput, Typyinput, Postalinput, addButton, deleteButton, editButton);


            TableView tableView = new TableView();
            try {
                tableView.setItems(getOrganization());
            } catch (NoSuchElementException e) {

            }
            tableView.getColumns().addAll(name, id, coordinates, creationDate, annualTurnover, fullName, employeesCount, type, postalAddress);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableView, hBox);
            Scene scene = new Scene(vBox);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("../Resourses/icon.png"))));
            stage.show();


            deleteButton.setOnAction(event1 -> {
                ObservableList<Organization> orgSelected, orgAll;
                orgAll = tableView.getItems();
                orgSelected = tableView.getSelectionModel().getSelectedItems();
                orgSelected.forEach(orgAll::remove);
                for (Organization o : orgSelected) {
                    RemoveById removeById = new RemoveById(o.getId());
                    try {
                        Send.send(removeById);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });

            addButton.setOnAction(event1 -> {
                if (Checkbool.checkString(Nameinput.getText()) && Checkbool.checkInt(Coordinatesinput.getText()) && Checkbool.checkDouble(Annualinput.getText()) && Checkbool.checkString(FullNameinput.getText()) && Checkbool.checkDouble(Employeeinput.getText()) && Checkbool.CheckTypeBool(Typyinput.getValue()) && Checkbool.checkInt(Postalinput.getText()) && Checkbool.checkInt(Idinput.getText())) {
                    Organization organization = new Organization(Integer.parseInt(Idinput.getText()), Nameinput.getText(), new Coordinates(Integer.parseInt(Coordinatesinput.getText()), 666), Double.parseDouble(Annualinput.getText()), FullNameinput.getText(), Long.parseLong(Employeeinput.getText()), Checkbool.CheckType(Typyinput.getValue()), new Address("Cпб", new Location(12.1, Integer.parseInt(Postalinput.getText()), 10, "Санкт-Петербург")));
                    Add add = new Add(organization);
                    tableView.getItems().add(organization);
                    try {
                        Send.send(add);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            editButton.setOnAction(event1 -> {


                if (Checkbool.checkString(Nameinput.getText()) && Checkbool.checkInt(Coordinatesinput.getText()) && Checkbool.checkDouble(Annualinput.getText()) && Checkbool.checkString(FullNameinput.getText()) && Checkbool.checkDouble(Employeeinput.getText()) && Checkbool.CheckTypeBool(Typyinput.getValue()) && Checkbool.checkInt(Postalinput.getText())) {
                    Organization organization = new Organization(Integer.parseInt(Idinput.getText()), Nameinput.getText(), new Coordinates(Integer.parseInt(Coordinatesinput.getText()), 666), Double.parseDouble(Annualinput.getText()), FullNameinput.getText(), Long.parseLong(Employeeinput.getText()), Checkbool.CheckType(Typyinput.getValue()), new Address("Cпб", new Location(12.1, Integer.parseInt(Postalinput.getText()), 10, "Санкт-Петербург")));
                    Add add = new Add(organization);
                    tableView.getItems().add(organization);
                    try {
                        Send.send(add);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                ObservableList<Organization> orgSelected, orgAll;
                orgAll = tableView.getItems();
                orgSelected = tableView.getSelectionModel().getSelectedItems();
                orgSelected.forEach(orgAll::remove);
                for (Organization o : orgSelected) {
                    RemoveById removeById = new RemoveById(o.getId());
                    try {
                        Send.send(removeById);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Nameinput.clear();
                Coordinatesinput.clear();
                Annualinput.clear();
                FullNameinput.clear();
                Employeeinput.clear();
                Postalinput.clear();
                Idinput.clear();
            });


        });

        logout.setOnAction(event -> {

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
            logout.getScene().getWindow().hide();
            stage.show();
        });

    }

    public void animate() {
        System.out.println("animate");
        Platform.runLater(() -> {
            outputanimation.getChildren().clear();
        });


        System.out.println("new animation");
        Group root = new Group();
        root.minHeight(800);
        root.minHeight(800);
        Timeline timeline = new Timeline();
        ArrayDeque<OrganizationId> arrayDeque = getOwnerOrganization();

        for (OrganizationId o : arrayDeque) {
            OrganizationCircle circle = new OrganizationCircle(0, 0, 25);
            circle.setId(o.getId());


            Color color = Color.color(
                    ((double) Math.abs(o.getOwner().hashCode() - 111) % 10) / 10,
                    ((double) Math.abs(o.getOwner().hashCode() - 33) % 10) / 10,
                    ((double) Math.abs(o.getOwner().hashCode() - 5955) % 10) / 10);

            StrokeTransition transition = new StrokeTransition(Duration.millis(1000), circle, color.darker(), color.brighter());
            transition.setCycleCount(-1);
            transition.setAutoReverse(true);
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
            transition1.setToX(o.getCoordinates().getX() % 800 + 100);
            transition1.setByY(o.getPostalAddress().getTown().getX() % 800 + 100);

            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0);
            ds.setColor(Color.color(0.4, 0.4, 0.4));
            circle.setEffect(ds);

            circle.setFill(color);
            root.getChildren().add(circle);
            circle.setOnMouseClicked(event1 -> {
                TextArea textField = new TextArea(o.toString());
                textField.setWrapText(false);

                TextField Nameinput, Coordinatesinput, Annualinput, FullNameinput, Employeeinput, Postalinput, Idinput;
                Nameinput = new TextField();
                Nameinput.setPromptText(Lang.getResourceBundle().getString("Name"));
                Nameinput.setMaxWidth(150);
                Coordinatesinput = new TextField();
                Coordinatesinput.setPromptText(Lang.getResourceBundle().getString("Coordinates"));
                Coordinatesinput.setMaxWidth(150);
                Annualinput = new TextField();
                Annualinput.setPromptText(Lang.getResourceBundle().getString("Annual Turnover"));
                Annualinput.setMaxWidth(150);
                FullNameinput = new TextField();
                FullNameinput.setPromptText(Lang.getResourceBundle().getString("Full name"));
                FullNameinput.setMaxWidth(150);
                Employeeinput = new TextField();
                Employeeinput.setPromptText(Lang.getResourceBundle().getString("Employee Count"));
                Employeeinput.setMaxWidth(150);
                ChoiceBox<String> Typyinput = new ChoiceBox<>(FXCollections.observableArrayList("частная", "трастовая", "государственная", "коммерческая"));
                Typyinput.setMaxWidth(125);
                Postalinput = new TextField();
                Postalinput.setPromptText(Lang.getResourceBundle().getString("Postal address"));
                Postalinput.setMaxWidth(150);
                Idinput = new TextField();
                Idinput.setPromptText(Lang.getResourceBundle().getString("Id"));
                Idinput.setMaxWidth(150);


                Button deleteButton = new Button(Lang.getResourceBundle().getString("Delete"));
                Button editButton = new Button(Lang.getResourceBundle().getString("Edit"));

                HBox hBox = new HBox();
                hBox.setPadding(new Insets(9, 9, 9, 9));
                hBox.setSpacing(9);
                hBox.getChildren().addAll(Nameinput, Idinput, Coordinatesinput, Annualinput, FullNameinput, Employeeinput, Typyinput, Postalinput, deleteButton, editButton);

                VBox vBox = new VBox();
//                    vBox.setAlignment(Pos.BASELINE_CENTER);
                textField.setWrapText(true);
                vBox.getChildren().addAll(hBox, textField);

                Stage stage1 = new Stage();
                Scene scene = new Scene(vBox);
                scene.setFill(Color.GRAY);

                stage1.setHeight(400);
                stage1.setWidth(900);
                stage1.setScene(scene);

                stage1.show();
                deleteButton.setOnAction(event2 -> {

                    if (o.getOwner().equals(username.getText())) {
                        RemoveById removeById = new RemoveById(o.getId());
                        try {
                            Send.send(removeById);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                            for (Node circle1 : root.getChildren()) {
                                if (((OrganizationCircle) circle1).getIdd() == o.getId()) {
                                    FadeTransition transition2 = new FadeTransition(Duration.seconds(3), circle);
                                    transition2.setFromValue(1.0);
                                    transition2.setToValue(0.0);
                                    transition2.play();
                                    transition2.setOnFinished(event3 -> root.getChildren().remove(circle));
                                    return;
                                }
                            }
                    }
                        timeline.stop();
                        timeline.play();
                });

                editButton.setOnAction(event2 -> {
                    Organization organization = null;
                    if (Checkbool.checkString(Nameinput.getText()) && Checkbool.checkInt(Coordinatesinput.getText()) && Checkbool.checkDouble(Annualinput.getText()) && Checkbool.checkString(FullNameinput.getText()) && Checkbool.checkDouble(Employeeinput.getText()) && Checkbool.CheckTypeBool(Typyinput.getValue()) && Checkbool.checkInt(Postalinput.getText())) {
                        organization = new Organization(Integer.parseInt(Idinput.getText()), Nameinput.getText(), new Coordinates(Integer.parseInt(Coordinatesinput.getText()), 666), Double.parseDouble(Annualinput.getText()), FullNameinput.getText(), Long.parseLong(Employeeinput.getText()), Checkbool.CheckType(Typyinput.getValue()), new Address("Cпб", new Location(12.1, Integer.parseInt(Postalinput.getText()), 10, "Санкт-Петербург")));
                        Add add = new Add(organization);
                        try {
                            Send.send(add);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    RemoveById removeById = new RemoveById(o.getId());
                    try {
                        Send.send(removeById);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    OrganizationCircle editedCircle = null;
                    for (Node circle1 : root.getChildren()) {
                        if (((OrganizationCircle) circle1).getIdd() == o.getId()) {
                            editedCircle = (OrganizationCircle) circle1;
                            FadeTransition transition2 = new FadeTransition(Duration.seconds(3), circle);
                            transition2.setFromValue(1.0);
                            transition2.setToValue(0.0);
                            transition2.play();
                            transition2.setOnFinished(event3 -> root.getChildren().remove(circle));
                            return;
                        }
                    }

                    editedCircle.setLayoutX(organization.getCoordinates().getX());
                    editedCircle.setLayoutY(organization.getPostalAddress().getTown().getX());
                    editedCircle.setId(organization.getId());

                    root.getChildren().add(editedCircle);

                    Nameinput.clear();
                    Coordinatesinput.clear();
                    Annualinput.clear();
                    FullNameinput.clear();
                    Employeeinput.clear();
                    Postalinput.clear();
                    Idinput.clear();
                });

            });

            Platform.runLater(() -> {
                transition.play();
                transition1.play();
            });
        }
        timeline.stop();
        timeline.play();

//            outputanimation.layout();
//            outputanimation.autosize();

        Platform.runLater(() -> {
            outputanimation.getChildren().add(root);
        });
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        changes(getAnimation(), Receive.arrayDeque, service);
    };


    ObservableList<Organization> getOrganization() {
        Show show = new Show();
        ObservableList<Organization> organizations = FXCollections.observableArrayList();
        try {
            Send.send(show);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Organization o : Receive.arrayDeque) {
            organizations.add(o);
        }
        return organizations;
    }

    public ObservableList<Organization> getAllOrganization() {
        ShowAll show = new ShowAll();
        ObservableList<Organization> organizations = FXCollections.observableArrayList();
        try {
            Send.send(show);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Organization o : Receive.arrayDeque) {
            System.out.println(o);
            organizations.add(o);
        }
        return organizations;
    }

    ArrayDeque<OrganizationId> getOwnerOrganization() {
        ShowOwner show = new ShowOwner();
        ShowAll showAll = new ShowAll();
        ArrayDeque<OrganizationId> arrayDeque = new ArrayDeque<>();
        try {
            Send.send(showAll);
            Send.send(show);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (OrganizationId o : Receive.organizationIds) {
            arrayDeque.add(o);
        }
        return arrayDeque;
    }

    private void changes(Button button, ArrayDeque<Organization> arrayDeque, ScheduledExecutorService service) {
        service.scheduleAtFixedRate(new Animation(arrayDeque, button, service, getOutputanimation(), this), 5, 5, TimeUnit.SECONDS);
    }

    public AnchorPane getOutputanimation() {
        return outputanimation;
    }

    public Button getAnimation() {
        return animation;
    }
//    public void checkK(){
//        if(k){
//            getAnimation().fire();
//            k=false;
//        }
//    }
}


