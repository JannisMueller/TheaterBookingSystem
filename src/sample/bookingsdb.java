package sample;

import java.sql.*;


public class bookingsdb {

    private static final String url = "jdbc:sqlserver://sqlserverjannis.database.windows.net:1433;database=BookingDb;user=Jannis@sqlserverjannis;password={Neuseeland1};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    public static void createBooking(Booking booking) {

        String bookingId = booking.getBookingID();
        String movie = booking.getMovie();
        String date = booking.getDate();
        String numberTickets = booking.getNumberOfTickets();
        String seats = booking.getSeats();
        int totalPrice = booking.getTotalPrice();

        //Create and execute a SELECT SQL statement.
        String sqlQuery1 = "INSERT INTO bookingTicket" +
                " (BookingId,Movie,[Date],NumberOfTickets, Seats,TotalPrice)" +
                " VALUES " +
                "(?, ?, ?, ?, ? ,?)";

        try (Connection connection = DriverManager.getConnection(url);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery1)) {

            preparedStatement.setString(1, bookingId);
            preparedStatement.setString(2, movie);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, numberTickets);
            preparedStatement.setString(5, seats);
            preparedStatement.setInt(6, totalPrice);

            preparedStatement.executeUpdate();

            }

        catch (SQLException e){
            e.printStackTrace();
        }

        }
    }
