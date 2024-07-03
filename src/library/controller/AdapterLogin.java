package library.controller;

import library.controller.graphic.LoginGraphicController;
import library.exception.DAOException;

import java.sql.SQLException;

public class AdapterLogin{

    private LoginGraphicController controller;

    public AdapterLogin(){}

    public AdapterLogin(LoginGraphicController controller){
        this.controller = controller;
    }

    public void doLogin() throws DAOException, SQLException {
        this.controller.submitActionLogin();
    }
}
