package com.levi.melodydirectory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateUserController implements Initializable {

    @FXML
    private VBox root = new VBox();

    @FXML
    private TextField usernameText = new TextField();

    @FXML
    private TextField passwordText = new TextField();

    @FXML
    private TextField repasswordText = new TextField();

    @FXML
    private Label errorText = new Label();

    /**
     *
     * @param event
     *
     * When user hits button, tries to create a new account
     */
    @FXML
    void createUserPressed(ActionEvent event) throws IOException {
        //checkDupUsername(usernameText.getText());

        if (true) {//Does this username already exist in DataBase?
            if (passwordText.getText().equals(repasswordText.getText())) {//Do passwords match?
                //success
                System.out.println("Username: " + usernameText.getText());
                System.out.println("Password: " + passwordText.getText());
                
                //set login status of application and return to homepage
                App.STATUS = HeaderBar.Status.LOGGED_IN;
                App.setRoot("Homepage");
            } else {
                errorText.setText("Passwords Must Be Identical");
                errorText.setVisible(true);
            }
        } else {
            errorText.setText("Username Is Already Taken");
            errorText.setVisible(true);
        }
    }

    /**
     *
     * @param arg0
     * @param arg1
     *
     * Adds header bar to top of page
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        root.getChildren().add(0, new HeaderBar(HeaderBar.Page.DEFAULT));
    }

}
