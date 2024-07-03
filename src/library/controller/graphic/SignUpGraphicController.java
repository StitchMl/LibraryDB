package library.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import library.bean.SignUpBean;
import library.controller.Controller;
import library.controller.SignUpController;
import library.exception.DAOException;
import library.model.domain.Role;

public class SignUpGraphicController extends RegisterGraphicController{

    /** Reference to controller*/
    protected SignUpController signUpController;

    /* All the text field of the interface*/
    @FXML
    private TextField username;

    /**Reference to bean*/
    protected final SignUpBean bean = new SignUpBean();

    /* All the checkbox of the interface*/
    @FXML
    private CheckBox adminTick;
    @FXML
    private CheckBox librarianTick;

    @FXML
    private void tickAction(ActionEvent event) {
        if (event.getSource() == adminTick && librarianTick.isSelected()) {
            librarianTick.setSelected(false);
        } else if (event.getSource() == librarianTick && adminTick.isSelected()) {
            adminTick.setSelected(false);
        } else if (event.getSource() == adminTick && !librarianTick.isSelected()) {
            librarianTick.setSelected(true);
        } else if (event.getSource() == librarianTick && !adminTick.isSelected()) {
            adminTick.setSelected(true);
        }
    }

    /** The action of the buttons*/
    @FXML
    protected void submitActionSignUp() throws DAOException {
        if(this.bean.checkEmpty(username) || this.bean.checkEmpty(password)){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(signUpController.getApp().getPrimaryStage());
            alert.setTitle("Field empty");
            alert.setHeaderText("A field is empty");
            alert.setContentText("Please fill username, email and password field");
            alert.showAndWait();
        }

        Role role = null;
        if (isLibrarian()){
            role = Role.LIBRARIAN;
        } else if (isAdmin()){
            role = Role.ADMINISTRATOR;
        }

        String userValue = username.getText();                  //get user entered username
        String passValue = password.getText();                  //get user entered password
        signUpController.submitActionSignUp(userValue, passValue, role);
    }

    public boolean isLibrarian(){
        return librarianTick.isSelected();
    }

    public boolean isAdmin(){
        return adminTick.isSelected();
    }

    /** Is called to set Controller*/
    public void setController(Controller contr) {
        this.signUpController = (SignUpController) contr;
        super.setController(contr);
    }
}
