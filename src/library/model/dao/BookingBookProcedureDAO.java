package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Book;
import library.model.domain.Utente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class BookingBookProcedureDAO implements GenericProcedureDAO<Integer>{
    @Override
    public Integer execute(Object... params) throws DAOException {
        Book book = (Book) params[0];
        Utente user = (Utente) params[1];
        String ISBN = book.getISBN();
        int ID = book.getIdCopy();
        String CF = user.CF();
        String temp = (String) params[2];
        int status;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call booking_book(?,?,?,?)}");
            cs.setInt(1, ID);
            cs.setString(2, ISBN);
            cs.setString(3, CF);
            cs.setString(4, temp);
            cs.executeUpdate();
            status = 1;
        } catch(SQLException e) {
            throw new DAOException("BookingList error: " + e.getMessage());
        }

        return status;
    }
}
