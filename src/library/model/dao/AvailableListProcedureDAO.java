package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Book;
import library.model.domain.BookList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailableListProcedureDAO implements GenericProcedureDAO<BookList>{
    @Override
    public BookList execute(Object... params) throws DAOException {
        String title = (String) params[0];
        String ISBN = (String) params[1];
        BookList bookList = new BookList();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call get_book_availability(?,?)}");
            cs.setString(1, title);
            cs.setString(2, ISBN);
            boolean status = cs.execute();

            if(status) {
                ResultSet rs = cs.getResultSet();
                int i = 1;
                while (rs.next()) {
                    Book book = new Book(i, rs.getString(6), rs.getInt(1), rs.getString(7),
                            rs.getString(2), rs.getString(3));
                    bookList.addAvailableBook(book);
                    i++;
                }
            }
        } catch(SQLException e) {
            throw new DAOException("BookList error: " + e.getMessage());
        }

        return bookList;
    }
}
