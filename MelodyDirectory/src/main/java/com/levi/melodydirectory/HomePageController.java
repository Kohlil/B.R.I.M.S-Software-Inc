package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl Date: 10/19/21 Purpose: Allows the GUI to trigger events
 * in the code. Connects home page to back-end of the code and loads LoginPage
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class HomePageController {

    @FXML // fx:id="loginPage"
    private Button loginButton;

    @FXML // fx:id="searchBar"
    private TextField searchBar;

    @FXML // fx:id="topRatedSlider"
    private ListView<?> topRatedSlider;

    @FXML // fx:id="recentlyAddedSlider"
    private ListView<?> recentlyAddedSlider;

    

    /**
     *
     * @param event
     * @throws IOException
     *
     * Switches HomePage to LoginPage
     */
    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    
    /**
     * 
     * @param event
     * @throws IOException
     * 
     * Switches view to Logged out
     */
    @FXML
    void logoutButtonPressed(ActionEvent event) throws IOException {
        App.setRoot("Homepage");
            // TODO add code to logout user in back-end
    }

    /**
     *
     * @param event
     *
     * Used to know when user is typing in the search bar, maybe have this send
     * a request to the database to constantly update possible results?
     */
    @FXML
    void keyTyped(KeyEvent event) {

    }
}
