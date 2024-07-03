package library.model.dao;

import library.exception.DAOException;
import library.model.domain.User;
import library.model.domain.Role;

import java.sql.*;
import java.util.Objects;

public class LoginProcedureDAO implements GenericProcedureDAO<User> {

    @Override
    public User execute(Object... params) throws DAOException, SQLException {
        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);
        } catch(SQLException e) {
            throw new DAOException("Login error: " + e.getMessage());
        }

        if(role != 3) {
            ConnectionFactory.changeRole(Objects.requireNonNull(Role.fromInt(role)));
            return new User(username, password, Role.fromInt(role));
        } else {
            return null;
        }
    }
}
