package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class LogInPage extends Application {

    public static TextField tfEmail = new TextField();

    @Override
    public void start(Stage stage2) {

        // Header
        Text txtLogin = new Text("Sign In");
        txtLogin.setId("header-text");

        Text notACustomer = new Text("Not a customer yet? Register now!");

        tfEmail.setPromptText("E-Mail");

        // Password
        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Password");

        // Buttons
        Button bntLogIn = new Button("Sign In");
        Button bntCreateAccount = new Button("Create Account");
        // Button -> Errortext
        Label lblErrorSignIn = new Label("Incorrect details, please try again");
        lblErrorSignIn.setVisible(false);

        // Logo
        Image imageSF = new Image("file:src/sample/logoCompany.jpg");
        ImageView imageViewSF = new ImageView(imageSF);
        imageViewSF.setPreserveRatio(true);
        imageViewSF.setFitHeight(120);
        imageViewSF.setFitWidth(120);

        // Creating and formatting gridpane
        GridPane gridPane2 = new GridPane();
        gridPane2.setVgap(10);
        gridPane2.setHgap(10);
        gridPane2.setAlignment(Pos.CENTER);

        // Adding objects to gridpane
        gridPane2.add(txtLogin, 1, 3, 2, 1);
        gridPane2.add(tfEmail, 1, 4);
        gridPane2.add(pfPassword, 2, 4);
        gridPane2.add(bntLogIn, 1, 6);
        gridPane2.add(lblErrorSignIn, 2, 6);
        gridPane2.add(notACustomer, 1, 9, 2, 1);
        gridPane2.add(bntCreateAccount, 1, 10);

        // Creating scene, etc
        stage2.setTitle("Log-in");
        Scene scene2 = new Scene(gridPane2, 450, 320);
        scene2.getStylesheets().add("sample/stylesheet.css");
        stage2.setScene(scene2);
        stage2.show();

        // Button -> Create Account
        // Goes to Create Account
        bntCreateAccount.setOnAction(actionEvent -> {
            stage2.close();
            CreateAccount stage = new CreateAccount();
            stage.start(stage2);
        });

        // Button -> Log in
        // Tests if account exists in database
        bntLogIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String passedInUserName = tfEmail.getText();
                String passedInPassword = pfPassword.getText();

                try {
                    Connection conn = DriverManager.getConnection(Datasource.CONNECTION_STRING);

                    String sql =     "SELECT * FROM " + Datasource.TABLE_CUSTOMER + " " +
                                     "WHERE " + Datasource.COLUMN_EMAIL + " = ? " +
                                     "AND " + Datasource.COLUMN_PASSWORD + " = ?";

                    PreparedStatement st = conn.prepareStatement(sql);
                    st.setString(1, passedInUserName);
                    st.setString(2, passedInPassword);

                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        Payment payment = new Payment();
                        payment.start(stage2);

                    } else {
                        lblErrorSignIn.setVisible(true);
                        System.out.println("User not found");
                    }

                } catch (
                        SQLException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
