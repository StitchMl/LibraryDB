package library.model.domain;

import java.sql.Date;

public record Utente(String CF, String name, String surname, String pref, String mail, String phone, String cell,
                     Date bornDate, String address) {

    @Override
    public String toString() {
        return this.name + " " + this.surname + " - " + this.CF;
    }
}
