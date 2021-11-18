package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl
 * Date: 10/19/21
 * Purpose: Allows the Login GUI to trigger events in the code.
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader
    
    @FXML // fx:id="loginErrorLabel"
    private Label loginErrorLabel; // Value injected by FXMLLoader

    
    
    /**
     * 
     * @param event
     * 
     * When loginButton is pressed this method is run
     */
    @FXML
    void buttonPressed(ActionEvent event) throws IOException {
        //if the user input any username and password
        if (!passwordTextField.getText().equals("") && !usernameTextField.getText().equals("")) {
            System.out.println("Username: " + usernameTextField.getText());//TEMP
            System.out.println("Password: " + passwordTextField.getText());//TEMP
            if (true) { //method in user profile class login() to validate credentials and login user on back-end
                App.STATUS = HeaderBar.Status.LOGGED_IN;
                App.setRoot("Homepage");
                
            }
        }
        //the username and password fields are blank
        else {
            loginErrorLabel.setVisible(true);
        }
    }
    
    /**
     * 
     * @param event 
     * 
     * Detects if user hit "enter" key when entering username and password
     * as a shortcut to hitting the loginButton
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
        //App.setRoot("CreateAccount");
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

}
