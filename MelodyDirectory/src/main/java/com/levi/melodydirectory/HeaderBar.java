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

    //Defines whether a user is logged in or logged out
    public static enum Status {
        LOGGED_IN,
        LOGGED_OUT,
    }
    
    //what type of page header bar is on
    public static enum Page {
        LOGINPAGE,
        HOMEPAGE,
        ADDSONG,
        DEFAULT
    }
    
    //whether adminBox should be shown or not
    public static boolean isAdmin = false;

    //FXML fields
    @FXML
    private Button loginPage = new Button();

    @FXML
    private Button logoutButton = new Button();

    @FXML
    private Button homeButton = new Button();
    
    @FXML
    private Button faqButton = new Button();

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton = new Button();
    
    @FXML
    private Button addButton = new Button();
    
    @FXML
    private Button requestsButton = new Button();


    /**
     * 
     * @param page where the header bar is
     * 
     * Bar for the top of all pages to standardize methods and searches
     */
    public HeaderBar(Page page) {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("HeaderBar.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
            if (App.STATUS == Status.LOGGED_IN) {//user is logged in so show logout button
                logoutButton.setVisible(true);
                logoutButton.setDisable(false);
                loginPage.setVisible(false);
                loginPage.setDisable(true);
            } 
            else {//user is logged out so show login button
                logoutButton.setVisible(false);
                logoutButton.setDisable(true);
                loginPage.setVisible(true);
                loginPage.setDisable(false);
                addButton.setVisible(false);
                addButton.setDisable(true);
            }
            if (page == Page.HOMEPAGE) {//homepage so no home button, but show FAQ
                this.homeButton.setVisible(false);
                this.homeButton.setDisable(true);
                faqButton.setVisible(true);
                faqButton.setDisable(false);   
            }
            else {
                this.homeButton.setVisible(true);
                this.homeButton.setDisable(false);
                faqButton.setVisible(false);
                faqButton.setDisable(true);
            }
            if (page == Page.LOGINPAGE) {//no login button, show logout
                loginPage.setVisible(false);
                loginPage.setDisable(true);
                logoutButton.setVisible(false);
                logoutButton.setDisable(true);
            }
            if (page == Page.ADDSONG) {//no add button when on add song page
                addButton.setVisible(false);
                addButton.setDisable(true);
            }
            if (!isAdmin && !App.isRequest) {//show admin box only when logged in user has admmin priveleges
                requestsButton.setVisible(false);
                requestsButton.setDisable(true);
            } else {
                requestsButton.setVisible(true);
                requestsButton.setDisable(false);
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
        //NOT NEEDED
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
        isAdmin = false;
        logoutButton.setVisible(false);
        logoutButton.setDisable(true);
        loginPage.setVisible(true);
        loginPage.setDisable(false);
        App.isRequest = false;
        
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
    
    /**
     * 
     * @param event
     * 
     * Switches to FAQ page
     */
    @FXML
    void faqButtonPressed(ActionEvent event) throws IOException {
        App.setRoot("FAQ");
    }
    
    /**
     * 
     * @param event 
     * 
     * add button was pressed, loads AddSong page
     */
    @FXML
    void addButtonPressed(ActionEvent event) throws IOException {
        App.setRoot("AddSong");
    }
    
    /**
     * 
     * @param event 
     * 
     * loads requests page
     */
    @FXML
    void RequestsButtonPressed(ActionEvent event) throws IOException {
        App.isRequest = true;
        App.setData("12345test");//really bad way of having a special string that loads all requests
        App.setRoot("SearchResults");//instead of performing search
    }
}
