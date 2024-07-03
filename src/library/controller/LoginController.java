package library.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import library.controller.graphic.LoginGraphicController;
import library.exception.DAOException;
import library.exception.NewException;

import java.sql.SQLException;

public class LoginController extends AccessController {
    /** Reference to graphicController*/
    private LoginGraphicController graphicController;

    /* Error login variable*/
    private Double delay = 3.0;
    private final Label label = new Label();

    /** It's called to load the home overview*/
    public void home(){
        this.app.setUser(this.user);
        this.app.showHomeOverview();
    }

    /** Check whether the credentials are authentic or not and do the right action*/
    public void submit(String userValue, String passValue) throws DAOException, SQLException {
        if (this.submit.login(userValue, passValue)) {   //if authentic, navigate user to a new page
            this.user = this.submit.getUser();
            home();
        } else {
            Throwable cause = new Throwable("The cause of the exception is in submit(), Login Class");
            String message = "Exception rose in submit() method, Login Class";
            NewException ne = new NewException(message,cause);
            try {
                throw ne;
            } catch (NewException e) {
                //show error message
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(app.getPrimaryStage());
                alert.setTitle("Wrong Username or Password");
                alert.setHeaderText("You wrote wrong username or password");
                alert.setContentText("Please enter valid username and password or Signup");
                setLoginDisable(true);
                Stage stage = createCountdown();
                alert.showAndWait();
                stage.showAndWait();
                delay = delay*(delay/2.0);
                setLoginDisable(false);
            }
        }
    }

    /** Countdown of error login*/
    private void doTime(){
        final int[] seconds = {delay.intValue()};
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(delay), event -> {
            seconds[0]--;
            label.setText("Waiting... " + seconds[0]);
            if(seconds[0] <=0){
                Stage stage = (Stage)label.getParent().getScene().getWindow();
                stage.close();
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /** Is called to activate or disable the login interface*/
    private void setLoginDisable(boolean bool){
        graphicController.getUsernameField().setDisable(bool);
        graphicController.getPassField().setDisable(bool);
        graphicController.getPasswordField().setDisable(bool);
        graphicController.getSubmitButton().setDisable(bool);
    }

    /** Is called to create countdown window*/
    private Stage createCountdown(){
        int seconds;
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initOwner(app.getPrimaryStage());
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        seconds = delay.intValue();
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font(20));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setText("Waiting... " + seconds);
        doTime();
        Pane root = new Pane();
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 70));
        return stage;
    }

    /** Is called to set graphicController*/
    public void setGraphicController(LoginGraphicController graphicController) {
        this.graphicController = graphicController;
    }
}

