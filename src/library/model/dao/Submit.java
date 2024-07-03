package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Role;
import library.model.domain.User;
import library.model.domain.Utente;

import java.sql.Date;
import java.sql.SQLException;

public class Submit {
    private User account = null;
    private Integer user = null;

    public User getUser(){
        return this.account;
    }
    public Integer getUtente(){return this.user;}

    /** Login method*/
    public boolean login(String userValue, String passValue) throws DAOException, SQLException {
        LoginProcedureDAO login = new LoginProcedureDAO();
        account = login.execute(userValue, passValue);
        return account != null;
    }
    /** SignUp method*/
    public void signUp(String userValue, String passValue, Role role) throws DAOException {
        account = new SignUpProcedureDAO().execute(new User(userValue, passValue, role));
    }

    /** Register method*/
    public void registerUser(String cfValue, String nameValue, String surnameValue, String pref, String emailValue,
                             String phoneValue, String cellValue, Date dateValue, String addressValue) throws DAOException {
        user = new SignUtenteProcedureDAO().execute(new Utente(cfValue, nameValue, surnameValue, pref, emailValue, phoneValue, cellValue, dateValue, addressValue));
    }
}