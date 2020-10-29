package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SelectMovie extends Application {

    public void start(Stage stage) {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time =  new Date(System.currentTimeMillis());
        String bookingTime = formatter.format(time);

        // Header text
        Text tHeader = new Text("Get Tickets");
        tHeader.setId("header-text");

        // Seperator
        Separator separator1 = new Separator();

        //Select Date
        DatePicker datePicker = new DatePicker();
        Text tDatePicker = new Text("Date: ");

        // Select movie
        Text tMovie = new Text("Select movie: ");
        ObservableList<String> optionsMovies =
                FXCollections.observableArrayList(
                        "Batman (9€)",
                        "Goodfellas (9€)",
                        "Beck (9€)"
                );
        ComboBox cbMovie = new ComboBox(optionsMovies);
        cbMovie.setPromptText("Select Movie");

        // Select seats
        Text tSeats = new Text("Number of tickets: ");
        ObservableList<String> optionsTickets =
                FXCollections.observableArrayList(
                        "1",
                        "2",
                        "3",
                        "4",
                        "5"
                );
        ComboBox cbTickets = new ComboBox(optionsTickets);
        cbTickets.setPromptText("Select tickets");

        // Next button
        Button btNext = new Button("Next");

        Image imageSF = new Image("file:src/sample/logoCompany.jpg");
        ImageView imageViewSF = new ImageView(imageSF);
        imageViewSF.setPreserveRatio(true);
        imageViewSF.setFitHeight(120);
        imageViewSF.setFitWidth(120);

        // Creating gridpane
        GridPane gridPane1 = new GridPane();

        // Formatting gridpane
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.TOP_CENTER);

        // Creating grid-pane layout
        gridPane1.add(tHeader,1,4);
        gridPane1.add(separator1,1,5);
        gridPane1.add(datePicker,1,6);
        gridPane1.add(tDatePicker,0,6);
        gridPane1.add(tMovie,0,7);
        gridPane1.add(cbMovie,1,7);
        gridPane1.add(tSeats, 0,8);
        gridPane1.add(cbTickets,1,8);
        gridPane1.add(btNext,1,9);
        gridPane1.add(imageViewSF,3,1);


        // Creating stage etc
        Scene scene1 = new Scene(gridPane1, 450, 500);
        scene1.getStylesheets().add("sample/stylesheet.css");
        stage.setScene(scene1);
        stage.show();


        btNext.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                try (Connection conn = DriverManager.getConnection(Datasource.CONNECTION_BOOKING_STRING);
                     Statement statement = conn.createStatement()) {

                    String bookingId = "RX-" + (int) (1 + Math.random() * 9999);
                    String movie = (String) cbMovie.getValue();
                    String date = String.valueOf(datePicker.getValue());
                    String numberTickets = (String) (cbTickets.getValue());
                    String seats = "Free choice of seats";
                    int totalPrice = Integer.parseInt((String) cbTickets.getValue()) * 9;

                    Datasource.createBooking(statement, bookingId, bookingTime, movie, date, numberTickets, seats, totalPrice);

                } catch (
                        SQLException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    e.printStackTrace();
                }


                stage.close();
                LogInPage logInPage = new LogInPage();
                logInPage.start(stage);
            }});

    }
}
