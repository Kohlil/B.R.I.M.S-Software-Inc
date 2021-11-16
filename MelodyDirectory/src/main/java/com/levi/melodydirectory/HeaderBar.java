/**
 * Author: Isaiah Kohl
 * Date: 11/5/21
 * Purpose: Standardized bar for top of all Pages/Views, contains login/logout,
 * search bar, and back button
 */
package com.levi.melodydirectory;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author isaia
 */
public class HeaderBar extends HBox {

    public static enum Status {
        LOGGED_IN,
        LOGGED_OUT
    }

    @FXML
    private Button loginPage = new Button();

    @FXML
    private Button logoutButton = new Button();

    @FXML
    private Button homeButton;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton = new Button();

    public HeaderBar(Boolean homeButton) {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HeaderBar.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
            if (App.STATUS == Status.LOGGED_IN) {
                logoutButton.setVisible(true);
                logoutButton.setDisable(false);
                loginPage.setVisible(false);
                loginPage.setDisable(true);
            } else {
                logoutButton.setVisible(false);
                logoutButton.setDisable(true);
                loginPage.setVisible(true);
                loginPage.setDisable(false);
            }
            if (!homeButton) {
                this.homeButton.setVisible(false);
                this.homeButton.setDisable(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    /**
     *
     * @param event
     *
     * User has clicked search bar, deletes placeholder text
     */
    @FXML
    void mouseClicked(MouseEvent event) {
        if (searchBar.getText().equals("Search")) {
            searchBar.setText("");
        }
    }

    /**
     *
     * @param event
     *
     * Performs search and loads search results page
     */
    @FXML
    void searchButtonPressed(ActionEvent event) throws IOException {
        App.setData(searchBar.getText());
        //switch to results page
        App.setRoot("SearchResults");
    }

    /**
     *
     * @param event
     *
     * Detects if user hit "enter" key when entering search
     */
    @FXML
    void searchShortcut(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            searchButtonPressed(new ActionEvent());
        }
    }

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
        App.STATUS = HeaderBar.Status.LOGGED_OUT;
        logoutButton.setVisible(false);
        logoutButton.setDisable(true);
        loginPage.setVisible(true);
        loginPage.setDisable(false);
    }

    /**
     *
     * @param event
     *
     * Switches to Homepage without interfering with login/logout status
     */
    @FXML
    void homeButtonPressed(ActionEvent event) throws IOException {
        App.setRoot("Homepage");
    }
}
