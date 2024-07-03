package library.controller.graphic;

import library.bean.LoginBean;
import library.controller.AdapterLogin;
import library.controller.Controller;
import library.controller.LoginController;
import library.exception.DAOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginGraphicController extends AccessGraphicController{

    /** All the text field of the interface*/
    @FXML
    private TextField user;
    @FXML
    private TextField loginPassText;

    /** All the button of the interface*/
    @FXML
    private Button submit;

    /** Reference to controller*/
    private LoginController loginController;

    /**Reference to bean instance*/
    private final LoginBean bean = new LoginBean();

    /** Get method*/
    public TextField getUsernameField(){
        return this.user;
    }
    public TextField getPassField(){
        return this.loginPassText;
    }
    public TextField getPasswordField(){
        return this.password;
    }
    public Button getSubmitButton(){
        return this.submit;
    }

    /** The action of the buttons*/
    @FXML
    public void makeLogin() throws DAOException, SQLException {
        AdapterLogin adapterLogin;
        adapterLogin = new AdapterLogin(this);
        adapterLogin.doLogin();
    }
    public void submitActionLogin() throws DAOException, SQLException {
        String userValue = "";
        if(bean.userCheck(user.getText())) {
            userValue = user.getText(); //get user entered username from the textField1
        }else{
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(loginController.getApp().getPrimaryStage());
            alert.setTitle("User is empty");
            alert.setHeaderText("The user field is empty");
            alert.setContentText("Please enter a username.");
            alert.showAndWait();
        }
        String passValue;
        if(passToggle.isSelected()) {
            if(bean.passCheck(this.loginPassText.getText())){
                passValue = this.loginPassText.getText(); //get user entered password from the textField2
                loginController.submit(userValue, passValue);
            }else{
                alert();
            }
        } else {
            if(bean.passCheck(password.getText())){
                passValue = password.getText(); //get user entered password from the textField2
                loginController.submit(userValue, passValue);
            }else{
                alert();
            }
        }
    }

    public void alert(){
        //show error message
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(loginController.getApp().getPrimaryStage());
        alert.setTitle("Password is empty");
        alert.setHeaderText("The password field is empty");
        alert.setContentText("Please enter a password.");
        alert.showAndWait();
    }

    /** Is called to set gymEditController*/
    public void setController(Controller controller) {
        this.loginController = (LoginController) controller;
        super.setController(controller);
    }

}
