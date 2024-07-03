package library.model.domain;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    final List<Book> availableBooks = new ArrayList<>();

    public void addAvailableBook(Book book) {
        this.availableBooks.add(book);
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Book book : availableBooks) {
            sb.append(book);
        }
        return sb.toString();
    }
}
