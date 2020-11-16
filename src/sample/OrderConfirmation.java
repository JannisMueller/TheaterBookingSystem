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

        CustomerDB.updateLoggedinCustomer(LogInPage.loggedInCustomer);

        //connection String to the SQL database
        String url = "jdbc:sqlserver://sqlserverjannis.database.windows.net:1433;database=BookingDb;user=Jannis@sqlserverjannis;password={Neuseeland1};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


        //declaring object booking (empty object; empty constructor)
        Booking bookingConfirmation = new Booking();

        String identifier = SelectMovie.booking.getBookingID();

        //sql query for getting the needed information
        String sqlQuery2 = "SELECT * FROM bookingTicket" +
                " WHERE BookingId = ? ";


        ResultSet resultSet;

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery2)) {

            preparedStatement.setString(1, identifier);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                // filling the obejct booking with values from the database
               bookingConfirmation.setBookingID(resultSet.getString(1));
               bookingConfirmation.setDate(resultSet.getString(2));
               bookingConfirmation.setMovie(resultSet.getString(3));
               bookingConfirmation.setNumberOfTickets(resultSet.getString(4));
               bookingConfirmation.setSeats(resultSet.getString(5));
               bookingConfirmation.setTotalPrice(resultSet.getInt(6));
               bookingConfirmation.setFirstName(LogInPage.loggedInCustomer.getFirstname());
               bookingConfirmation.setLastName(LogInPage.loggedInCustomer.getLastname());
               bookingConfirmation.setEmail(LogInPage.loggedInCustomer.getEmail());
               bookingConfirmation.setPhone(LogInPage.loggedInCustomer.getPhone());

            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }


            Text bookingInformation = new Text("Booking Details");
            bookingInformation.setId("header-text");

            Label bookingId = new Label("Booking number");
            Text txtBookingId = new Text(bookingConfirmation.getBookingID());
            bookingId.setId("bold-text");

            Label nameFilm = new Label("Movie");
            Text txtNameFilm = new Text(bookingConfirmation.getMovie());
            nameFilm.setId("bold-text");

            Label date = new Label("Date");
            Text txtDate = new Text(bookingConfirmation.getDate());
            date.setId("bold-text");

            Label numberTickets = new Label("Number of Tickets");
            Text txtNumberTickets = new Text(bookingConfirmation.getNumberOfTickets());
            numberTickets.setId("bold-text");

            Label seat = new Label("Seat");
            Text txtSeat = new Text(bookingConfirmation.getSeats());
            seat.setId("bold-text");

            Label totalPrice = new Label("Total Price");
            Text txtTotalPrice = new Text(bookingConfirmation.getTotalPrice() + " €");
            totalPrice.setId("bold-text");

            Label nameCustomer = new Label("Name");
            nameCustomer.setStyle("-fx-font-weight: bold");
            Text txtNameCustomer = new Text(bookingConfirmation.getFirstName() + " " + bookingConfirmation.getLastName());
            nameCustomer.setId("bold-text");

            Label emailCustomer = new Label("Email-Address");
            Text txtEmailCustomer = new Text(bookingConfirmation.getEmail());
            emailCustomer.setId("bold-text");

            Label phoneCustomer = new Label("Phone Number");
            Text txtPhoneCustomer = new Text(bookingConfirmation.getPhone());
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

    }
}
