package sample;

import javafx.application.Application;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccount extends Application {

    public void start(Stage stage) {

        // Header text
        Text tHeader = new Text("Create account");
        tHeader.setId("header-text");

        // First name
        Label lblFirstName = new Label("First name: ");
        TextField tfFirstName = new TextField();
        tfFirstName.setPromptText("First name");

        // Last name
        Label lblLastName = new Label("Last name");
        TextField tfLastName = new TextField();
        tfLastName.setPromptText("Last name");

        // E-Mail
        Label lblEmail = new Label("E-Mail: ");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("E-Mail");

        // Phone number
        Label lblPhone = new Label("Phone: ");
        TextField tfPhone = new TextField();
        tfPhone.setPromptText("Phone");

        // Password
        Label lblPassword = new Label("Password: ");
        PasswordField pfPassword = new PasswordField();

        // Buttons
        Button btnCreateAccount = new Button("Create Account");
        Button btnBack = new Button("Back");

        // Action for Button CreateAccount
        btnCreateAccount.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                // Connect to db
                try (Connection conn = DriverManager.getConnection(Datasource.CONNECTION_STRING);
                     Statement statement = conn.createStatement()) {

                    // Save customer data to Strings for easier readability
                    String firstname = tfFirstName.getText();
                    String lastname = tfLastName.getText();
                    String email = tfEmail.getText();
                    String phone = tfPhone.getText();
                    String password = pfPassword.getText();

                    // Creates a position in the customer db
                    Datasource.createCustomer(statement, firstname, lastname, email, phone, password);

                } catch (
                        SQLException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                    e.printStackTrace();
                }

                // Alert pop-up notifying that account was created
                Alert alertAccountCreated = new Alert(Alert.AlertType.INFORMATION);
                alertAccountCreated.setTitle("Information Dialog");
                alertAccountCreated.setHeaderText("Account created!");
                alertAccountCreated.setContentText("Log in with your details on next page");
                alertAccountCreated.showAndWait();

                // Switch to log-in page
                LogInPage logInPageStage = new LogInPage();
                logInPageStage.start(stage);

            }
        });
        
        // Action for Button Bac
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LogInPage logInPageStage = new LogInPage();
                logInPageStage.start(stage);
            }
        });

        // Logo
        Image imageSF = new Image("file:src/sample/logoCompany.jpg");
        ImageView imageViewSF = new ImageView(imageSF);
        imageViewSF.setPreserveRatio(true);
        imageViewSF.setFitHeight(120);
        imageViewSF.setFitWidth(120);

        // Creating and formatting gridpane
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);

        // Adding objects to gridpane
        gridPane.add(imageViewSF, 3, 1);

        gridPane.add(tHeader, 1, 4, 2, 1);

        gridPane.add(lblFirstName, 1, 6);
        gridPane.add(tfFirstName, 2, 6);

        gridPane.add(lblLastName, 1, 7);
        gridPane.add(tfLastName, 2, 7);

        gridPane.add(lblEmail, 1, 8);
        gridPane.add(tfEmail, 2, 8);

        gridPane.add(lblPhone, 1, 9);
        gridPane.add(tfPhone, 2, 9);

        gridPane.add(lblPassword, 1, 10);
        gridPane.add(pfPassword, 2, 10);

        gridPane.add(btnCreateAccount, 2, 11);
        gridPane.add(btnBack, 2, 12);

        Scene scene2 = new Scene(gridPane, 450, 500);
        scene2.getStylesheets().add("sample/stylesheet.css");
        stage.setScene(scene2);
        stage.show();


    }

}
