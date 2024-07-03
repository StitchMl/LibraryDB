package library.controller.graphic;

import library.controller.AccessController;
import library.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

abstract class AccessGraphicController{

    /** Reference to controller*/
    protected AccessController controller;

    /** Text field of the interface*/
    @FXML
    private TextField loginPassText;
    @FXML
    protected TextField password;

    //Label
    @FXML
    protected Label eye;

    /** All the checkbox of the interface*/
    @FXML
    protected CheckBox passToggle;

    /** Controls the visibility of the password*/
    @FXML
    protected void setTogglePass(){
        if(!this.passToggle.isSelected()) {
            eye.setStyle("-fx-text-fill: #6c6b6b;");
            passToggle.setSelected(true);
            controller.getApp().togglevisiblePassword(this.passToggle, this.loginPassText, this.password);
        } else {
            eye.setStyle("-fx-text-fill: black;");
            passToggle.setSelected(false);
            controller.getApp().togglevisiblePassword(this.passToggle, this.loginPassText, this.password);
        }
    }

    /** Is called to set controller*/
    public void setController(Controller controller) {
        this.controller = (AccessController) controller;
    }

}
