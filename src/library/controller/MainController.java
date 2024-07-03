package library.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import library.Main;
import library.controller.graphic.*;
import library.model.dao.DAO;
import library.model.dao.UpdateType;
import library.model.dao.Submit;
import library.model.domain.*;
import library.utilities.Dialog;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    /* The variable of all application*/
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Submit submit;
    private User user = null;
    private String value1 = "";
    private String value2 = "";
    private Boolean close = false;
    private final DAO dao = new DAO();

    /* Set method*/
    public void setSubmit(Submit submit) {
        this.submit = submit;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    /* Get method*/

    public Boolean getClose() {
        return close;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public DAO getDAO() {
        return this.dao;
    }
    public BorderPane getPrimaryPane() {
        return rootLayout;
    }

    /** Initializes the root layout.*/
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            // Load root layout from fxml file.
            loader.setLocation(Main.class.getResource("View/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 830, 550);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    /** Shows login overview inside the root layout.*/
    public void showLoginOverview() {
        LoginController controller = new LoginController();
        LoginGraphicController graphicController;
        try {
            this.primaryStage.setTitle("Library - Login");

            // Load login overview.
            FXMLLoader loaderLogin = new FXMLLoader();
            loaderLogin.setLocation(Main.class.getResource("View/Login.fxml"));
            Pane pane = loaderLogin.load();

            // Setting gymEditController
            graphicController = loaderLogin.getController();
            controller.setGraphicController(graphicController);
            graphicController.setController(controller);
            controller.setApp(this);
            controller.setSubmit(this.submit);
            controller.setUser(this.user);

            // Set login overview into the center of root layout.
            this.getPrimaryPane().setCenter(pane);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    /** Shows sign up overview inside the root layout.*/
    public void showSignUpOverview() {
        try {
            this.getPrimaryStage().setTitle("Library - Register");
            // Load sign up overview.
            FXMLLoader loaderSignUp = new FXMLLoader();
            if(user.role() == Role.ADMINISTRATOR) {
                loaderSignUp.setLocation(Main.class.getResource("View/SignUp.fxml"));
            } else {
                loaderSignUp.setLocation(Main.class.getResource("View/Register.fxml"));
            }
            Pane pane = loaderSignUp.load();

            // Set sign up overview into the center of root layout.
            this.getPrimaryPane().setCenter(pane);

            // Give the gymEditController access to the main app.
            if(user.role() == Role.ADMINISTRATOR) {
                SignUpGraphicController graphicController = loaderSignUp.getController();
                SignUpController controller = new SignUpController();
                graphicController.setController(controller);
                controller.setApp(this);
            } else {
                RegisterUserGraphicController graphicController = loaderSignUp.getController();
                RegisterUserController controller = new RegisterUserController();
                graphicController.setController(controller);
                controller.setApp(this);
                graphicController.setDate();
            }

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    /** Shows home overview inside the root layout.*/
    public void showHomeOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            this.primaryStage.setTitle("Library - Home");
            HomeController controller = new HomeController();
            // Load home overview.
            loader.setLocation(Main.class.getResource("View/Home.fxml"));

            Pane homeOverview = loader.load();

            // Set home overview into the center of root layout.
            rootLayout.setCenter(homeOverview);
            rootLayout.setTop(null);

            // Give the gymEditController access to the main app.
            HomeGraphicController graphicController = loader.getController();
            controller.setGraphicController(graphicController);
            graphicController.setController(controller);
            controller.setApp(this);
            controller.setUser(this.user);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    /** Controls the visibility of the Password field*/
    public void togglevisiblePassword(CheckBox passToggle, TextField passText, TextField password) {
        if (passToggle.isSelected()) {
            passText.setText(password.getText());
            passText.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(passText.getText());
        password.setVisible(true);
        passText.setVisible(false);
    }

    public void showReportOverview() {
        try {
            BookingList desc = this.dao.getReport();
            FXMLLoader loader = new FXMLLoader();
            this.primaryStage.setTitle("Library - Report");
            ReportController controller = new ReportController();
            // Load home overview.
            loader.setLocation(Main.class.getResource("View/Report.fxml"));

            Pane bookOverview = loader.load();

            // Set home overview into the center of root layout.
            rootLayout.setCenter(bookOverview);
            rootLayout.setTop(null);

            // Give the gymEditController access to the main app.
            ReportGraphicController graphicController = loader.getController();
            graphicController.setController(controller);
            graphicController.setReportDescription(desc);
            controller.setApp(this);
            controller.setUser(this.user);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void showBookListOverview(String... sList) {
        if(sList.length==0) {
            CountDownLatch modalitySignal = new CountDownLatch(1);
            new Thread(() -> {
                Dialog dialog = new Dialog();
                dialog.setApp(this);
                dialog.setWait(modalitySignal);
                dialog.createAndShowGUI("Title", "ISBN");
            }).start();
            try {
                modalitySignal.await();
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
            if (close) {
                setClose(false);
                return;
            }
        } else {
            value1 = sList[0];
            value2 = sList[1];
        }
        try {
            BookList desc = this.dao.getAvailable(value1, value2);
            FXMLLoader loader = new FXMLLoader();
            this.primaryStage.setTitle("Library - Book");
            BookListController controller = new BookListController();
            // Load home overview.
            loader.setLocation(Main.class.getResource("View/BookList.fxml"));

            Pane bookOverview = loader.load();

            // Set home overview into the center of root layout.
            rootLayout.setCenter(bookOverview);
            rootLayout.setTop(null);

            // Give the gymEditController access to the main app.
            BookListGraphicController graphicController = loader.getController();
            graphicController.setController(controller);
            graphicController.setBookDescription(desc);
            controller.setApp(this);
            controller.setUser(this.user);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void showChangeStateOverview() {
        if(!value1.matches("^[0-9]+")){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getPrimaryStage());
            alert.setTitle("Error format!");
            alert.setHeaderText("The ID format is wrong");
            alert.setContentText("Please the ID must be a number");
            alert.showAndWait();
        }
        int status = dao.updateProcedure(UpdateType.CHANGE_STATE, null, new Book(0,value2, Integer.parseInt(value1),"","",""), "disponibile");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(this.getPrimaryStage());
        alert.setTitle("Get Book Copy.");
        alert.setHeaderText("Get Copies");
        if(status == 1){
            alert.setContentText("Some value are wrong.");
        } else {
            alert.setContentText("Great! The Copy has been returned.");
        }
        alert.showAndWait();
    }
}
