package sample;

import java.sql.*;

public class Datasource {

    // Columns for customer db
    public static final String DB_NAME = "customer.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:file:src\\" + DB_NAME;
    public static final String TABLE_CUSTOMER = "customer";

    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";


    // Columns for booking details db
    public static final String DB_BOOKING_NAME = "bookings.db";
    public static final String CONNECTION_BOOKING_STRING = "jdbc:sqlite:file:src/" + DB_BOOKING_NAME;
    public static final String TABLE_BOOKINGS = "bookings";

    public static final String COLUMN_TIME_BOOKING = "bookingTime";
    public static final String COLUMN_BOOKING_ID = "bookingId";
    public static final String COLUMN_MOVIE = "movie";
    public static final String COLUMN_DATEMOVIE = "dateMovie";
    public static final String COLUMN_NUMBER_TICKETS = "numberTickets";
    public static final String COLUMN_SEATS = "seats";
    public static final String COLUMN_TOTAL_PRICE = "totalPrice";



    public static void createBookingsDatabase() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_BOOKING_STRING);

             Statement statement = conn.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS " + Datasource.TABLE_BOOKINGS +

                    " (" + COLUMN_BOOKING_ID + " text, " +
                    COLUMN_TIME_BOOKING + " text , " +
                    COLUMN_MOVIE + " text, " +
                    COLUMN_DATEMOVIE + " text, " +
                    COLUMN_NUMBER_TICKETS + " text, " +
                    COLUMN_SEATS+ " text, " +
                    COLUMN_TOTAL_PRICE+ " integer" +

                    ")");

        } catch (
                SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createBooking(Statement statement, String bookingId, String bookingTime, String movie, String date, String numberTickets, String seats, int totalPrice) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_BOOKINGS +
                " (" + COLUMN_BOOKING_ID+ ", " +
                COLUMN_TIME_BOOKING + ", " +
                COLUMN_MOVIE + ", " +
                COLUMN_DATEMOVIE + ", " +
                COLUMN_NUMBER_TICKETS + ", " +
                COLUMN_SEATS+ " , " +
                COLUMN_TOTAL_PRICE +
                " ) " +
                "VALUES('" + bookingId + "', '"+  bookingTime + "', '" +  movie + "', '" + date + "', '" + numberTickets + "', '" + seats + "', " + totalPrice + ")");
    }


    public static void createCustomerDatabase() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);

             Statement statement = conn.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS " + Datasource.TABLE_CUSTOMER +
                    " (" + COLUMN_FIRSTNAME + " text, " +
                    COLUMN_LASTNAME + " text, " +
                    COLUMN_EMAIL + " text, " +
                    COLUMN_PHONE + " text, " +
                    COLUMN_PASSWORD + " text" +
                    ")");

        } catch (
                SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createCustomer(Statement statement, String firstname, String lastname, String email, String phone, String password) throws SQLException {

        statement.execute("INSERT INTO " + TABLE_CUSTOMER +
                " (" + COLUMN_FIRSTNAME + ", " +
                COLUMN_LASTNAME + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_PASSWORD +
                " ) " +
                "VALUES('" + firstname + "', '" + lastname + "', '" + email + "', '" + phone + "' , '" + password + "')");
    }

}
