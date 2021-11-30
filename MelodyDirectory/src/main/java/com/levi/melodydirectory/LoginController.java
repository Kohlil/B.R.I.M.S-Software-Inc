package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl Date: 10/19/21 Purpose: Allows the Login GUI to trigger
 * events in the code.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class LoginController implements Initializable {

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="loginErrorLabel"
    private Label loginErrorLabel; // Value injected by FXMLLoader

    @FXML
    private VBox root = new VBox();

    /**
     *
     * @param event
     *
     * When loginButton is pressed this method is run
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {
        try {
            //if the user input any username and password and they exist in DB
            DBreturn db = DBreturn.getInstance();
            if (!passwordTextField.getText().equals("") && !usernameTextField.getText().equals("")
                    && db.retrieveUser(usernameTextField.getText(), passwordTextField.getText())) {
                App.STATUS = HeaderBar.Status.LOGGED_IN;
                App.setRoot("Homepage");
                //the username and password fields are blank
            }
            else {
                loginErrorLabel.setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(5);
        }
    }

    /**
     *
     * @param event
     *
     * Detects if user hit "enter" key when entering username and password as a
     * shortcut to hitting the loginButton
     */
    @FXML
    void loginShortcut(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            buttonPressed(new ActionEvent());
        }
    }

    /**
     *
     * @param event
     * @throws IOException
     *
     * New User? button was clicked, loads CreateAccount page
     */
    @FXML
    void newUserPressed(ActionEvent event) throws IOException {
        App.setRoot("CreateUser");
    }

    /**
     *
     * @param event
     * @throws IOException
     *
     * Home button was pressed, loads HomePage view
     */
    @FXML
    void homePressed(ActionEvent event) throws IOException {
        App.setRoot("Homepage");
    }

    /**
     *
     * @param arg0
     * @param arg1
     *
     * Adds header bar to top of page
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1
    ) {
        root.getChildren().add(0, new HeaderBar(HeaderBar.Page.LOGINPAGE));
    }

}
