package library.model.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    final SimpleIntegerProperty n;
    final SimpleStringProperty ISBN;
    final SimpleIntegerProperty idCopy;
    final SimpleStringProperty title;
    final SimpleStringProperty shelf;
    final SimpleStringProperty level_ground;

    public Book(Integer n, String ISBN, Integer idCopy, String title, String shelf, String level_ground) {
        this.n = new SimpleIntegerProperty(n);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.idCopy = new SimpleIntegerProperty(idCopy);
        this.title = new SimpleStringProperty(title);
        this.shelf = new SimpleStringProperty(shelf);
        this.level_ground = new SimpleStringProperty(level_ground);
    }

    // Set method
    public int getN() {
        return n.get();
    }

    public String getISBN(){
        return this.ISBN.get();
    }

    public int getIdCopy() {
        return idCopy.get();
    }

    public String getTitle(){
        return this.title.get();
    }

    public String getShelf(){
        return this.shelf.get();
    }

    public String getLevel_ground(){
        return this.level_ground.get();
    }

    @Override
    public String toString() {
        return ISBN + " - " + idCopy + ": " + title + " - " + shelf +
                "/" + level_ground;
    }
}
