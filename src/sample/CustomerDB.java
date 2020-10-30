package sample;

import java.sql.*;

public class CustomerDB {

    private static final String connectionUrl = "jdbc:sqlserver://sqlserverjannis.database.windows.net:1433;database=BookingDb;user=boalbert@sqlserverjannis;password={login123!};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    ResultSet resultSet = null;

    public static void createCustomer() {

        try (Connection conn = DriverManager.getConnection(connectionUrl);
            Statement statement = conn.createStatement();) {

            String sql = "INSERT INTO customer (FirstName, LastName, Email, Password, Phone) VALUES " +
                    "('Albert', 'Andersson', 'andersson.albert@gmail.com', 'losenord', '0730550834')";

            statement.execute(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}

/*

    public static void testSelection() {

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
//            String selectSql = "SELECT TOP 1 LastName from bookingTicket";

            String selectSql = "INSERT INTO bookingTicket (FirstName, LastName, email) VALUES ('Albert', 'Andersson', 'andersson.albert@gmail.com')";

            statement.execute(selectSql);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }


 */