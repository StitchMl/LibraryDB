package library.controller;

import javafx.scene.control.Alert;
import library.exception.DAOException;
import library.model.dao.Submit;
import library.model.domain.Role;

public class SignUpController extends AccessController {

    private Submit submitSignUp;

    /** The constructor.*/
    public SignUpController() {
        this.submitSignUp = new Submit();
    }

    @Override
    public void setApp(MainController app){
        this.app = app;
        this.submitSignUp = new Submit();
    }


    /** Is called to sign up*/
    public void submitActionSignUp(String userValue, String passValue, Role role) throws DAOException {
        if (!userValue.equals("") && !passValue.equals("")) { //if authentic, navigate user to a new page
              submitSignUp.signUp(userValue, passValue, role);
              user = submitSignUp.getUser();
        } else {
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("Empty field");
            alert.setHeaderText("Some obligatory value are empty");
            alert.setContentText("Please enter all obligatory value.");
            alert.showAndWait();
        }
        if(user == null){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("User already exists");
            alert.setHeaderText("The user already exists");
            alert.setContentText("Please enter a different username or login.");
            alert.showAndWait();
        } else {
            app.showHomeOverview();
        }
    }

}


