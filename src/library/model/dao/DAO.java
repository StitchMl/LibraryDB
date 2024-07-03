package library.model.dao;

import library.exception.DAOException;
import library.model.domain.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    private static final Logger LOGGER  =   Logger.getLogger(DAO.class.getName());

    /** It's called to change user*/
    public void changeUser(Role role) throws SQLException {
        ConnectionFactory.changeRole(role);
    }

    /** It's called to get data from DB*/
    public BookList getAvailable(String title, String ISBN){
        BookList result;
        try{
            AvailableListProcedureDAO b = new AvailableListProcedureDAO();
            result = b.execute(title, ISBN);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public BookingList getReport(){
        BookingList result;
        try{
            BookingListProcedureDAO b = new BookingListProcedureDAO();
            result = b.execute();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /** It's called to update data in DB*/
    public Integer updateProcedure(UpdateType procedure, Object... value){
        Utente u = null;
        Book b = null;
        String s = null;
        if(value.length!=0) {
            u = (Utente) value[0];
            b = (Book) value[1];
            s = (String) value[2];
        }
        int status = 0;
        try{
            if(procedure == UpdateType.BOOKING_BOOK){
                BookingBookProcedureDAO p = new BookingBookProcedureDAO();
                status = p.execute(b, u, s);
            } else if(procedure == UpdateType.CHANGE_STATE){
                ChangeStateProcedureDAO p = new ChangeStateProcedureDAO();
                status = p.execute(b, s);
            } else if(procedure == UpdateType.REMOVE_BOOK){
                RemoveBookProcedureDAO p = new RemoveBookProcedureDAO();
                status = p.execute();
            }
        }catch (DAOException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return status;
    }
}
