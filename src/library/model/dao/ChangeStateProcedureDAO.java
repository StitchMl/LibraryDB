package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Book;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ChangeStateProcedureDAO implements GenericProcedureDAO<Integer> {
    @Override
    public Integer execute(Object... params) throws DAOException {
        Book book = (Book) params[0];
        String ISBN = book.getISBN();
        int ID = book.getIdCopy();
        String state = (String) params[1];
        int status;
        int modify;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call change_copia_state(?,?,?)}");
            cs.setInt(1, ID);
            cs.setString(2, ISBN);
            cs.setString(3, state);

            modify = cs.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("ChangeState error: " + e.getMessage());
        }

        if(modify>0) {
            status = 1;
        } else {
            status = 0;
        }

        return status;
    }
}
