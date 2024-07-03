package library.controller.graphic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.controller.BookListController;
import library.controller.Controller;
import library.utilities.DialogBooking;
import library.model.dao.UpdateType;
import library.model.domain.Book;
import library.model.domain.BookList;
import library.model.domain.Utente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookListGraphicController{

    private static final Logger LOGGER = Logger.getLogger(BookListGraphicController.class.getName());
    /* All the button of the interface*/
    @FXML
    protected Button back;
    @FXML
    protected TableView<Book> reportDescription;
    @FXML
    protected TableColumn<Book, Integer> n;
    @FXML
    protected TableColumn<Book, Integer> isbn;
    @FXML
    protected TableColumn<Book, Integer> id;
    @FXML
    protected TableColumn<Book, String> title;
    @FXML
    protected TableColumn<Book, String> shelf;
    @FXML
    protected TableColumn<Book, String> level_ground;

    /** Reference to controller*/
    protected BookListController controller;
    private Book b;

    // add your data here from any source
    protected ObservableList<Book> bookModels = FXCollections.observableArrayList();

    public void setBookDescription(BookList desc){
        n.setCellValueFactory(new PropertyValueFactory<>("n"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        id.setCellValueFactory(new PropertyValueFactory<>("idCopy"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        shelf.setCellValueFactory(new PropertyValueFactory<>("shelf"));
        level_ground.setCellValueFactory(new PropertyValueFactory<>("level_ground"));
        bookModels.addAll(desc.getAvailableBooks());
        reportDescription.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = reportDescription.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    this.b = reportDescription.getItems().get(selectedIndex);
                    this.bookingBook();
                }
            }
        });
        reportDescription.setItems(bookModels);
    }

    private void bookingBook() {
        String value1 = controller.getApp().getValue1();
        String value2 = controller.getApp().getValue2();
        CountDownLatch modalitySignal = new CountDownLatch(1);
        new Thread(() -> {
            DialogBooking dialog = new DialogBooking();
            dialog.setApp(this.controller.getApp());
            dialog.setWait(modalitySignal);
            dialog.createAndShowGUI();
        }).start();
        try {
            modalitySignal.await();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            Thread.currentThread().interrupt();
        }
        if(controller.getApp().getClose()){
            controller.getApp().setClose(false);
            return;
        }
        String cf = controller.getApp().getValue1();
        String s = controller.getApp().getValue2();
        Utente u = new Utente(cf,"", "", "", "", "", "", Date.valueOf(LocalDate.now()), "");
        int status = controller.getApp().getDAO().updateProcedure(UpdateType.BOOKING_BOOK, u, b, s);
        if(status != 1){
            //show error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(controller.getApp().getPrimaryStage());
            alert.setTitle("Copy not booked!");
            alert.setHeaderText("The copy has not been booked");
            alert.setContentText("The copy could not be booked. Try again.");
            alert.showAndWait();
        } else {
            controller.getApp().showBookListOverview(value1, value2);
        }
    }

    /* The action of the button*/
    @FXML
    private void backAction() {
        controller.getApp().showHomeOverview();
    }

    public void setController(Controller controller) {
        this.controller = (BookListController) controller;
    }

}
