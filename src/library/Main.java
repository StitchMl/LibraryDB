package library;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.controller.MainController;
import library.model.dao.Submit;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainController app = new MainController();
        app.setSubmit(new Submit());
        app.setPrimaryStage(primaryStage);

        // Set the application.
        app.getPrimaryStage().getIcons().add( new Image( Objects.requireNonNull(
                getClass().getResourceAsStream("Images/Library icon.png"))));
        app.initRootLayout();
        app.showLoginOverview();
    }
}