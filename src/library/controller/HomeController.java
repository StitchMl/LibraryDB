package library.controller;

import library.controller.graphic.HomeGraphicController;
import library.model.domain.Role;
import library.model.domain.User;

public class HomeController extends Controller {

    /** Reference to graphicController*/
    private HomeGraphicController homeGraphicController;

    /** Is called to set user.*/
    @Override
    public void setUser(User user) {
        this.user = user;
        homeGraphicController.getSignOut().setVisible(this.user != null);
        if(this.user != null) {
            homeGraphicController.getSignOut().setPrefWidth(141);
            homeGraphicController.getReport().setVisible(this.user.role() != Role.ADMINISTRATOR);
            if(this.user.role() != Role.ADMINISTRATOR) {
                homeGraphicController.getReport().setPrefWidth(141);
            } else {
                homeGraphicController.getReport().setPrefWidth(0);
            }
        } else {
            homeGraphicController.getSignOut().setPrefWidth(0);
        }
    }

    /** Is called to set graphicController*/
    public void setGraphicController(HomeGraphicController graphicController) {
        this.homeGraphicController = graphicController;
    }
}