package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import static sample.bookingsdb.createBooking;


public class Main extends Application {

    public static void main(String[] args) {

        // Set up database if it does not exist
        Datasource.createCustomerDatabase();
        Datasource.createBookingsDatabase();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SelectMovie selectMovieStage = new SelectMovie();
        selectMovieStage.start(stage);
        stage.show();

    }
}
