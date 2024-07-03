package library.model.domain;

import java.util.ArrayList;
import java.util.List;

public class BookingList {
    private final List<BookingBook> bookingBooks = new ArrayList<>();

    public void addBookingBook(BookingBook bookingBook) {
        this.bookingBooks.add(bookingBook);
    }

    public List<BookingBook> getBookingBooks() {
        return bookingBooks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(BookingBook bookingBook : bookingBooks) {
            sb.append(bookingBook.toString()).append('\n');
        }
        return sb.toString();
    }
}
