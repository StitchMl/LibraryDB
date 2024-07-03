package library.controller.graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import library.controller.Controller;
import library.controller.HomeController;
import library.model.dao.UpdateType;
import library.model.dao.Submit;
import library.model.domain.Role;
import java.sql.SQLException;

public class HomeGraphicController{

    /* All the button of the interface*/
    @FXML
    protected Button signOut;
    @FXML
    protected Button book;
    @FXML
    protected Button register;
    @FXML
    protected Button report;

    /** Reference to controller*/
    protected HomeController controller;

    /** Get method*/
    public Button getSignOut(){return this.signOut;}
    public Button getReport(){return this.report;}

    /* The action of the button*/
    @FXML
    private void signOutAction() throws SQLException {
        controller.setUser(null);
        controller.getApp().setUser(null);
        controller.getApp().setSubmit(new Submit());
        controller.getApp().getDAO().changeUser(Role.LOGIN);
        controller.getApp().showLoginOverview();
    }

    @FXML
    public void bookAction() {
        if (controller.getUser().role() == Role.ADMINISTRATOR){
            int status = controller.getApp().getDAO().updateProcedure(UpdateType.REMOVE_BOOK);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(controller.getApp().getPrimaryStage());
            alert.setTitle("Remove Book.");
            alert.setHeaderText("Books Dismiss");
            if(status == 1){
                alert.setContentText("Some books have been dismiss.");
            } else {
                alert.setContentText("No books have been dismiss.");
            }
            alert.showAndWait();
        } else if (controller.getUser().role() == Role.LIBRARIAN){
            controller.getApp().showBookListOverview();
        }
    }

    @FXML
    public void reportAction() {
        controller.getApp().showReportOverview();
    }

    @FXML
    public void registerAction() {
        controller.getApp().showSignUpOverview();
    }

    public void setController(Controller controller) {
        this.controller = (HomeController) controller;
    }
}
