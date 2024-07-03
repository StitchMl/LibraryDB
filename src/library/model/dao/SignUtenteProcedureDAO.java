package library.model.dao;

import library.exception.DAOException;
import library.model.domain.Utente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class SignUtenteProcedureDAO implements GenericProcedureDAO<Integer>{
    @Override
    public Integer execute(Object... params) throws DAOException {
        Utente u = (Utente) params[0];
        String cf = u.CF();
        String name = u.name();
        String surname = u.surname();
        String pref = u.pref();
        String mail = u.mail();
        String phone = u.phone();
        String cell = u.cell();
        Date bday = u.bornDate();
        String address = u.address();
        int status;
        int modify;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call iscrivi_utente(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, cf);
            cs.setString(2, name);
            cs.setString(3, surname);
            cs.setString(4, pref);
            cs.setString(5, mail);
            cs.setString(6, phone);
            cs.setString(7, cell);
            cs.setDate(8, bday);
            cs.setString(9, address);

            modify = cs.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("Register utente error: " + e.getMessage());
        }

        if(modify>0) {
            status = 1;
        } else {
            status = 0;
        }

        return status;
    }
}
