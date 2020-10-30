package sample;

import java.sql.*;

public class CustomerDB {

    private static final String connectionUrl = "jdbc:sqlserver://sqlserverjannis.database.windows.net:1433;database=BookingDb;user=boalbert@sqlserverjannis;password={login123!};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    ResultSet resultSet = null;

    public static void createCustomer(Customer newCustomer) {

        String firstname = newCustomer.getFirstname();
        String lastname = newCustomer.getLastname();
        String email = newCustomer.getEmail();
        String password = newCustomer.getPassword();
        String phone = newCustomer.getPhone();

        String sql =    "INSERT INTO customer (FirstName, LastName, Email, Password, Phone) " +
                        "VALUES " +
                        "(?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionUrl);
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, phone);

            int insertedRows = preparedStatement.executeUpdate();
            System.out.println("Inserted rows: " + insertedRows);

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