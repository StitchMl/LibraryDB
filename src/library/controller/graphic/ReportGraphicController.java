package library.controller.graphic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.controller.Controller;
import library.controller.ReportController;
import library.model.domain.BookingBook;
import library.model.domain.BookingList;

public class ReportGraphicController {

    /* All the button of the interface*/
    @FXML
    protected Button back;
    @FXML
    protected TableView<BookingBook> reportDescription;
    @FXML
    protected TableColumn<BookingBook, Integer> n;
    @FXML
    protected TableColumn<BookingBook, Integer> isbn;
    @FXML
    protected TableColumn<BookingBook, Integer> id;
    @FXML
    protected TableColumn<BookingBook, String> utente;
    @FXML
    protected TableColumn<BookingBook, String> cf;
    @FXML
    protected TableColumn<BookingBook, String> contact;
    @FXML
    protected TableColumn<BookingBook, String> remaining_time;

    /** Reference to controller*/
    protected ReportController controller;
    private BookingBook b;

    // add your data here from any source
    protected ObservableList<BookingBook> bookingModels = FXCollections.observableArrayList();

    public void setReportDescription(BookingList desc){
        n.setCellValueFactory(new PropertyValueFactory<>("n"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        id.setCellValueFactory(new PropertyValueFactory<>("idCopy"));
        utente.setCellValueFactory(new PropertyValueFactory<>("utente"));
        cf.setCellValueFactory(new PropertyValueFactory<>("CF"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        remaining_time.setCellValueFactory(new PropertyValueFactory<>("remaining_time"));
        bookingModels.addAll(desc.getBookingBooks());
        reportDescription.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = reportDescription.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    this.b = reportDescription.getItems().get(selectedIndex);
                    this.changeState();
                }
            }
        });
        reportDescription.setItems(bookingModels);
    }

    private void changeState() {
        controller.getApp().setValue1("" + b.getIdCopy());
        controller.getApp().setValue2(b.getISBN());
        controller.getApp().showChangeStateOverview();
        controller.getApp().showReportOverview();
    }

    /* The action of the button*/
    @FXML
    private void backAction() {
        controller.getApp().showHomeOverview();
    }

    public void setController(Controller controller) {
        this.controller = (ReportController) controller;
    }

}
