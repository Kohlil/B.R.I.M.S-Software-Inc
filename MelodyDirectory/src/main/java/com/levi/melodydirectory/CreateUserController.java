package com.levi.melodydirectory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            //checkDupUsername(usernameText.getText());
            DBreturn db = DBreturn.getInstance();
            if (!db.searchUser(usernameText.getText())) {//Does this username already exist in DataBase?
                if (passwordText.getText().equals(repasswordText.getText())) {//Do passwords match?
                    //success
                    db.createUser(usernameText.getText(), passwordText.getText());
                    
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
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
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
