package library.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import library.bean.SignUpBean;
import library.controller.Controller;
import library.controller.RegisterUserController;
import library.exception.DAOException;

import java.sql.Date;
import java.time.LocalDate;

public class RegisterUserGraphicController extends RegisterGraphicController{

    /** Reference to controller*/
    protected RegisterUserController registerController;

    /* All the text field of the interface*/
    @FXML
    private TextField cf;
    @FXML
    private DatePicker date;
    @FXML
    private TextField address;
    @FXML
    private TextField civ;
    @FXML
    private TextField cap;
    @FXML
    private TextField city;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField telefono;
    @FXML
    private TextField cellulare;

    /**Reference to bean*/
    protected final SignUpBean bean = new SignUpBean();

    /* All the checkbox of the interface*/
    @FXML
    private CheckBox emailTick;
    @FXML
    private CheckBox telefonoTick;
    @FXML
    private CheckBox cellulareTick;
    @FXML
    private CheckBox viaTick;
    @FXML
    private CheckBox piazzaTick;
    @FXML
    private CheckBox vialeTick;

    public void setDate(){
        date.setValue(LocalDate.now());
    }

    @FXML
    private void tickAction(ActionEvent event) {
        if (event.getSource() == emailTick) {
            telefonoTick.setSelected(false);
            cellulareTick.setSelected(false);
        } else if (event.getSource() == telefonoTick) {
            emailTick.setSelected(false);
            cellulareTick.setSelected(false);
        } else if (event.getSource() == cellulareTick) {
            emailTick.setSelected(false);
            telefonoTick.setSelected(false);
        }

        if (event.getSource() == telefonoTick && !telefonoTick.isSelected()) {
            telefonoTick.setSelected(true);
        } else if (event.getSource() == emailTick && !emailTick.isSelected()) {
            emailTick.setSelected(true);
        } else if (event.getSource() == cellulareTick && !cellulareTick.isSelected()) {
            cellulareTick.setSelected(true);
        }
    }
    @FXML
    private void tickViaAction(ActionEvent event) {
        if (event.getSource() == viaTick) {
            piazzaTick.setSelected(false);
            vialeTick.setSelected(false);
            viaTick.setSelected(true);
        } else if (event.getSource() == piazzaTick) {
            viaTick.setSelected(false);
            vialeTick.setSelected(false);
        } else if (event.getSource() == vialeTick) {
            viaTick.setSelected(false);
            piazzaTick.setSelected(false);
        }

        if (event.getSource() == piazzaTick && !piazzaTick.isSelected()) {
            piazzaTick.setSelected(true);
            viaTick.setSelected(false);
        } else if (event.getSource() == vialeTick && !vialeTick.isSelected()) {
            vialeTick.setSelected(true);
        } else if (event.getSource() == viaTick && !viaTick.isSelected()) {
            viaTick.setSelected(true);
        }
    }

    /** The action of the buttons*/
    @FXML
    protected void submitActionRegister() throws DAOException {
        if(this.bean.checkEmpty(cf) || this.bean.checkEmpty(firstName)|| this.bean.checkEmpty(lastName)|| this.bean.checkEmpty(address)
                || this.bean.checkEmpty(civ) || this.bean.checkEmpty(cap) || this.bean.checkEmpty(city)){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(registerController.getApp().getPrimaryStage());
            alert.setTitle("Field empty");
            alert.setHeaderText("A field is empty");
            alert.setContentText("Please fill all field with star");
            alert.showAndWait();
        }

        if(!isCorrectCiv(civ.getText())){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(registerController.getApp().getPrimaryStage());
            alert.setTitle("Error format!");
            alert.setHeaderText("The civic format is wrong");
            alert.setContentText("Please write the civic in correct format");
            alert.showAndWait();
        }

        if(!isCorrectCAP(cap.getText())){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(registerController.getApp().getPrimaryStage());
            alert.setTitle("Error format!");
            alert.setHeaderText("The CAP format is wrong");
            alert.setContentText("Please write the CAP in correct format");
            alert.showAndWait();
        }

        String pref = null;
        if (isEmail()){
            pref = "e-mail";
        } else if (isCellulare()){
            pref = "cellulare";
        } else if (isTelefono()){
            pref = "telefono";
        }

        String addressValue = null;
        if (isVia()){
            addressValue = "Via ";
        } else if (isPiazza()){
            addressValue = "Piazza ";
        } else if (isViale()){
            addressValue = "Viale ";
        }

        String cfValue = cf.getText();
        String nameValue = firstName.getText();
        String surnameValue = lastName.getText();
        String emailValue = email.getText();
        String phoneValue = telefono.getText();
        String cellValue = cellulare.getText();
        Date dateValue = Date.valueOf(date.getValue());
        addressValue = addressValue + address.getText() + ", " + civ.getText() + ", " + cap.getText() + ", " + city.getText();
        registerController.submitActionRegister(cfValue, nameValue, surnameValue, pref, emailValue, phoneValue, cellValue, dateValue, addressValue);
    }

    public boolean isEmail(){
        return emailTick.isSelected();
    }

    public boolean isCellulare(){
        return cellulareTick.isSelected();
    }

    public boolean isTelefono(){
        return telefonoTick.isSelected();
    }

    public boolean isVia(){
        return viaTick.isSelected();
    }

    public boolean isPiazza(){
        return piazzaTick.isSelected();
    }

    public boolean isViale(){
        return vialeTick.isSelected();
    }

    public static boolean isCorrectCiv(String str) {
        return str.matches("^[0-9]+");
    }

    public static boolean isCorrectCAP(String str) {
        return str.matches("^[0-9]+");
    }

    /** Is called to set Controller*/
    public void setController(Controller contr) {
        this.registerController = (RegisterUserController) contr;
        super.setController(contr);
    }
}
