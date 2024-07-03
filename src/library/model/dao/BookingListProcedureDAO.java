package library.model.dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import library.exception.DAOException;
import library.model.domain.BookingBook;
import library.model.domain.BookingList;

import java.sql.*;

public class BookingListProcedureDAO implements GenericProcedureDAO<BookingList> {
    @Override
    public BookingList execute(Object... params) throws DAOException {
        BookingList bookingList = new BookingList();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call report_prestito()}");
            boolean status = cs.execute();

            if(status) {
                ResultSet rs = cs.getResultSet();
                int i = 1;
                while (rs.next()) {
                    BookingBook bookingBook = new BookingBook(new SimpleIntegerProperty(i),
                            rs.getDate(1), new SimpleStringProperty(rs.getString(2)),
                            new SimpleStringProperty(rs.getString(3)), new SimpleIntegerProperty(rs.getInt(4)),
                            new SimpleStringProperty(rs.getString(5)+ " " + rs.getString(6)),
                            new SimpleStringProperty(rs.getString(7)), new SimpleStringProperty(rs.getString(8)),
                            new SimpleStringProperty(rs.getString(9)), new SimpleStringProperty(rs.getString(10)),
                            new SimpleStringProperty(rs.getString(11)));
                    bookingList.addBookingBook(bookingBook);
                    i++;
                }
            }
        } catch(SQLException e) {
            throw new DAOException("BookingList error: " + e.getMessage());
        }

        return bookingList;
    }
}
