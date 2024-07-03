package library.model.dao;

import library.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RemoveBookProcedureDAO implements GenericProcedureDAO <Integer>{
    @Override
    public Integer execute(Object... params) throws DAOException {
        int status;
        int modify;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call remove_book()}");

            modify = cs.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("Remove book error: " + e.getMessage());
        }

        if(modify>0) {
            status = 1;
        } else {
            status = 0;
        }

        return status;
    }
}
