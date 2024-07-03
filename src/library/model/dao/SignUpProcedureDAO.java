package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Role;
import library.model.domain.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpProcedureDAO implements GenericProcedureDAO<User> {
    @Override
    public User execute(Object... params) throws DAOException {
        User u = (User) params[0];
        String username = u.username();
        String password = u.password();
        Role role = u.role();
        User user;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call crea_user(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            if (role == Role.LIBRARIAN){
                cs.setString(3, "bibliotecario");
            } else if (role == Role.ADMINISTRATOR){
                cs.setString(3, "amministratore");
            }

            cs.executeQuery();
            user = new User(username, password, role);
        } catch(SQLException e) {
            throw new DAOException("Sign up error: " + e.getMessage());
        }


        return user;
    }
}
