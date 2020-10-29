package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class OrderConfirmation extends Application {

    @Override
    public void start(Stage stage5) {

        //define sql string
        String sql =     "SELECT * FROM " + Datasource.TABLE_CUSTOMER + " " +
                        "WHERE " + Datasource.COLUMN_EMAIL + " = ? ";

        String sql2 = "SELECT * FROM " + Datasource.TABLE_BOOKINGS+ " " +
                    "ORDER BY " + Datasource.COLUMN_TIME_BOOKING+ " " +
                    " DESC LIMIT 1";

        String passedInUserName = LogInPage.tfEmail.getText();

        try {
            Connection conn = null;
            ResultSet result = null;
            PreparedStatement preparedStatements = null;

            // Connection to user db
            conn = DriverManager.getConnection(Datasource.CONNECTION_STRING);
            preparedStatements = conn.prepareStatement(sql);

            preparedStatements.setString(1,passedInUserName);

            result= preparedStatements.executeQuery();

            String firstName = result.getString(Datasource.COLUMN_FIRSTNAME);
            String lastName = result.getString(Datasource.COLUMN_LASTNAME);
            String email = result.getString(Datasource.COLUMN_EMAIL);
            String phone = result.getString(Datasource.COLUMN_PHONE);

            preparedStatements.close();
            result.close();
            conn.close();

            //connection to bookings db
            conn = DriverManager.getConnection(Datasource.CONNECTION_BOOKING_STRING);
            preparedStatements = conn.prepareStatement(sql2);
            result= preparedStatements.executeQuery();

            String resultBookingId = result.getString(Datasource.COLUMN_BOOKING_ID);
            String resultMovie = result.getString(Datasource.COLUMN_MOVIE);
            String resultDate = result.getString(Datasource.COLUMN_DATEMOVIE);
            String resultNumberTickets = result.getString(Datasource.COLUMN_NUMBER_TICKETS);
            String resultSeats = result.getString(Datasource.COLUMN_SEATS);
            String resultTotalPrice= result.getString(Datasource.COLUMN_TOTAL_PRICE);

            preparedStatements.close();
            result.close();
            conn.close();


            Text bookingInformation = new Text("Booking Details");
            bookingInformation.setId("header-text");

            Label bookingId = new Label("Booking number");
            Text txtBookingId = new Text(resultBookingId);
            bookingId.setId("bold-text");

            Label nameFilm = new Label("Movie");
            Text txtNameFilm = new Text(resultMovie);
            nameFilm.setId("bold-text");

            Label date = new Label("Date");
            Text txtDate = new Text(resultDate);
            date.setId("bold-text");

            Label numberTickets = new Label("Number of Tickets");
            Text txtNumberTickets = new Text(resultNumberTickets);
            numberTickets.setId("bold-text");

            Label seat = new Label("Seat");
            Text txtSeat = new Text(resultSeats);
            seat.setId("bold-text");

            Label totalPrice = new Label("Total Price");
            Text txtTotalPrice = new Text(resultTotalPrice + " €");
            totalPrice.setId("bold-text");

            Label nameCustomer = new Label("Name");
            nameCustomer.setStyle("-fx-font-weight: bold");
            Text txtNameCustomer = new Text(firstName + " " + lastName);
            nameCustomer.setId("bold-text");

            Label emailCustomer = new Label("Email-Address");
            Text txtEmailCustomer = new Text(email);
            emailCustomer.setId("bold-text");

            Label phoneCustomer = new Label("Phone Number");
            Text txtPhoneCustomer = new Text(phone);
            phoneCustomer.setId("bold-text");

            Text sendAsEmail = new Text("Send tickets to a friend?");

            Text info = new Text("Payment successful");
            info.setId("bold-text");

            TextField emailFriend = new TextField();
            emailFriend.setPromptText("Email-Address");

            Button bntCloseWindow = new Button("Close");
            Button btnSend = new Button("Send");
            Text emailSent = new Text();

            Image imagePaymentSuccess = new Image("file:src/sample/success2.gif");
            ImageView imageViewPaymentSuccess = new ImageView(imagePaymentSuccess);
            imageViewPaymentSuccess.setPreserveRatio(true);
            imageViewPaymentSuccess.setFitHeight(20);
            imageViewPaymentSuccess.setFitWidth(20);

            Image imageSF = new Image("file:src/sample/logoCompany.jpg");
            ImageView imageViewSF = new ImageView(imageSF);
            imageViewSF.setPreserveRatio(true);
            imageViewSF.setFitHeight(120);
            imageViewSF.setFitWidth(120);


            GridPane gridPane5 = new GridPane();
            gridPane5.setVgap(10);
            gridPane5.setHgap(10);
            gridPane5.setAlignment(Pos.TOP_CENTER);

            gridPane5.add(bookingInformation, 1, 4);

            gridPane5.add(bookingId, 1, 6);
            gridPane5.add(txtBookingId, 2, 6);

            gridPane5.add(nameFilm, 1, 7);
            gridPane5.add(txtNameFilm, 2, 7);

            gridPane5.add(date, 1, 8);
            gridPane5.add(txtDate, 2, 8);

            gridPane5.add(numberTickets, 1, 9);
            gridPane5.add(txtNumberTickets, 2, 9);

            gridPane5.add(seat, 1, 10);
            gridPane5.add(txtSeat, 2, 10);

            gridPane5.add(totalPrice, 1, 11);
            gridPane5.add(txtTotalPrice, 2, 11);

            gridPane5.add(nameCustomer, 1, 12);
            gridPane5.add(txtNameCustomer, 2, 12);

            gridPane5.add(emailCustomer, 1, 13);
            gridPane5.add(txtEmailCustomer, 2, 13);

            gridPane5.add(phoneCustomer,1,14);
            gridPane5.add(txtPhoneCustomer,2,14);

            gridPane5.add(info, 1, 5);

            gridPane5.add(bntCloseWindow, 1, 22);

            gridPane5.add(sendAsEmail, 1, 17);
            gridPane5.add(emailFriend, 1, 18);
            gridPane5.add(btnSend, 1, 19);
            gridPane5.add(emailSent, 1, 20);

            gridPane5.add(imageViewPaymentSuccess, 2, 5);

            gridPane5.add(imageViewSF, 3, 1);

            stage5.setTitle("Thank you for Order!");
            Scene scene5 = new Scene(gridPane5, 500, 650);
            scene5.getStylesheets().add("sample/stylesheet.css");
            stage5.setScene(scene5);
            stage5.show();


            // BUtton -> Close application
            bntCloseWindow.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage5.close();
                }
            });

            // Button -> Send ticket-info via e-mail (functionality not added)
            btnSend.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    emailSent.setText("Tickets sent to: " + emailFriend.getText());
                }
            });

        } catch (
                SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
