package library.model.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import library.utilities.DateUtilities;

import java.sql.Date;
import java.util.Objects;

public class BookingBook {

    private final SimpleIntegerProperty n;
    private final Date bookDate;
    private final SimpleStringProperty time;
    private final SimpleStringProperty ISBN;
    private final SimpleIntegerProperty idCopy;
    private final SimpleStringProperty utente;
    private final SimpleStringProperty CF;
    private final SimpleStringProperty mail;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty cell;
    private final SimpleStringProperty pref;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty remaining_time;

    public BookingBook(SimpleIntegerProperty n, Date bookDate, SimpleStringProperty time, SimpleStringProperty ISBN, SimpleIntegerProperty idCopy,
                       SimpleStringProperty utente, SimpleStringProperty CF, SimpleStringProperty mail,
                       SimpleStringProperty phone, SimpleStringProperty cell, SimpleStringProperty pref){
        this.n = n;
        this.bookDate = bookDate;
        this.time = time;
        this.ISBN = ISBN;
        this.idCopy = idCopy;
        this.utente = utente;
        this.CF = CF;
        this.mail = mail;
        this.phone = phone;
        this.cell = cell;
        this.pref = pref;
        if(Objects.equals(pref.get(), "telefono")){
            this.contact = phone;
        } else if(Objects.equals(pref.get(), "cellulare")){
            this.contact = cell;
        } else if(Objects.equals(pref.get(), "e-mail")){
            this.contact = mail;
        } else {
            this.contact = new SimpleStringProperty("");
        }
        this.remaining_time = new SimpleStringProperty(DateUtilities.calculateRemainTime(time.get(), bookDate) + " remaining day");
    }
    public int getN() {
        return n.get();
    }
    public String getISBN() {
        return ISBN.get();
    }
    public int getIdCopy() {
        return idCopy.get();
    }
    public String getUtente() {
        return utente.get();
    }
    public String getCF() {
        return CF.get();
    }
    public String getContact() {
        return contact.get();
    }
    public String getRemaining_time() {
        return remaining_time.get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n.get()).append("\t").append(ISBN.get()).append("\t").append(idCopy.get()).append("\t").append(utente.get())
                .append("\t").append(CF.get()).append("\t");
        if(Objects.equals(pref.get(), "telefono")){
            sb.append(phone.get());
        } else if (Objects.equals(pref.get(), "cellulare")){
            sb.append(cell.get());
        } else if (Objects.equals(pref.get(), "e-mail")){
            sb.append(mail.get());
        }
        sb.append("\t").append(DateUtilities.calculateRemainTime(time.get(), bookDate)).append(" remaining day");
        return sb.toString();
    }
}
