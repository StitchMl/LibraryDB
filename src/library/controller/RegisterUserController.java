package library.controller;

import javafx.scene.control.Alert;
import library.exception.DAOException;
import library.model.dao.Submit;

import java.sql.Date;

public class RegisterUserController extends AccessController {

    private Submit submitRegister;

    /** The constructor.*/
    public RegisterUserController() {
        this.submitRegister = new Submit();
    }

    @Override
    public void setApp(MainController app){
        this.app = app;
        this.submitRegister = new Submit();
    }


    /** Is called to sign up*/
    public void submitActionRegister(String cfValue, String nameValue, String surnameValue, String pref, String emailValue,
                                     String phoneValue, String cellValue, Date dateValue, String addressValue) throws DAOException {
        submitRegister.registerUser(cfValue, nameValue, surnameValue, pref, emailValue, phoneValue, cellValue, dateValue, addressValue);
        int user = submitRegister.getUtente();
        if(user != 1){
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


