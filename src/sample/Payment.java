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

public class Payment extends Application {


    @Override
    public void start(Stage stage4) {

        //DropDown menu for payment options
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Mastercard",
                        "Visa card",
                        "American Express");

        final ComboBox cbPaymentOptions = new ComboBox(options);
        cbPaymentOptions.setPromptText("Credit Card Options");

        TextField tfCreditCardNumber = new TextField();
        tfCreditCardNumber.setPromptText("Credit Card Number");

        TextField tfValid = new TextField();
        tfValid.setPromptText("Valid Thru (month-year)");

        PasswordField cvvField = new PasswordField();
        cvvField.setPromptText("CVV");

        Button btnConfirm = new Button("Confirm Payment");

        Button btnCancel = new Button("Cancel");

        CheckBox terms = new CheckBox(" I accept the Terms & Conditions");
        CheckBox saveCCInfo = new CheckBox("Save your credit card for faster checkout");

        Label lbCreditCardNumber = new Label("Credit Card Number");
        Label lbValid = new Label("Valid Thru");
        Label lbCVV = new Label("CVV");

        Text tInfo = new Text("©CinemaTix AB, 23143 Jönköping");
        tInfo.setId("footer");


        // Logo
        Image imageCC = new Image("file:src/sample/visacard.png");
        ImageView imageViewCC = new ImageView(imageCC);
        imageViewCC.setPreserveRatio(true);
        imageViewCC.setFitHeight(150);
        imageViewCC.setFitWidth(158);

        // Creating and formatting gridpane
        GridPane gridPane4 = new GridPane();
        gridPane4.setVgap(10);
        gridPane4.setHgap(10);
        gridPane4.setAlignment(Pos.CENTER);

        // Adding objects to gridpane
        gridPane4.add(imageViewCC, 1, 0);

        gridPane4.add(cbPaymentOptions, 1, 1);

        gridPane4.add(lbCreditCardNumber, 0, 2);
        gridPane4.add(tfCreditCardNumber, 1, 2);

        gridPane4.add(lbValid, 0, 3);
        gridPane4.add(tfValid, 1, 3);

        gridPane4.add(lbCVV, 0, 4);
        gridPane4.add(cvvField, 1, 4);

        gridPane4.add(saveCCInfo, 1, 5);
        gridPane4.add(terms, 1, 8);


        gridPane4.add(btnConfirm, 1, 9);
        gridPane4.add(btnCancel, 1, 10);

        gridPane4.add(tInfo, 0, 13);

        // Creating stage etc
        stage4.setTitle("Payment");
        Scene scene4 = new Scene(gridPane4, 400, 400);
        scene4.getStylesheets().add("sample/stylesheet.css");
        stage4.setScene(scene4);
        stage4.show();

        // Button -> Back to Select Movie
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SelectMovie stage = new SelectMovie();
                stage.start(stage4);

            }
        });

        // Button -> Go to Order Confirmation
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (terms.isSelected()) {
                    stage4.close();
                    OrderConfirmation stage5 = new OrderConfirmation();
                    stage5.start(stage4);

                } else {
                    terms.setStyle("-fx-text-fill: red");
                }
            }
        });
    }
}
