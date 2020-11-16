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

        String sql = "INSERT INTO customer (FirstName, LastName, Email, Password, Phone) " +
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

    public static boolean loginCustomer(String username, String password) {

        String sql =    "SELECT Email, Password " +
                        "FROM Customer " +
                        "WHERE Email = ? " +
                        "AND Password = ?";

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("User details correct!");
                resultSet.close();
                return true;

            } else {
                System.out.println("User details incorrect!");
                resultSet.close();
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }


}


