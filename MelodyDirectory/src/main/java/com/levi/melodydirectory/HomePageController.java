package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl Date: 10/19/21 Purpose: Allows the GUI to trigger events
 * in the code. Connects home page to back-end of the code and loads LoginPage
 */
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class HomePageController implements Initializable {

    @FXML // fx:id="loginPage"
    private Button loginButton;

    @FXML // fx:id="searchBar"
    private TextField searchBar;

    @FXML // fx:id="topRatedSlider"
    private ListView<Generic> topRatedSlider = new ListView<>();

    @FXML // fx:id="recentlyAddedSlider"
    private ListView<Generic> recentlyAddedSlider;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Generic> topRatedElementsObs = FXCollections.observableArrayList();
        ObservableList<Generic> recentlyAddedElementsObs = FXCollections.observableArrayList();

        //Element for testing only
        topRatedElementsObs.add(new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A", 7, 0, "SpiderMan Soundtrack", "$2.00", "2:00"));
        recentlyAddedElementsObs.add(new Artist("Post Malone", "The literal best", "Hip-Hop", "N/A", "N/A", 7, 0, new ArrayList<>()));

        // TO-DO Query DB for top Rated and recently added results, then
        
        topRatedSlider.setCellFactory(new ElementCellFactory());
        topRatedSlider.getItems().addAll(topRatedElementsObs);
        topRatedSlider.setEditable(false);
        topRatedSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        recentlyAddedSlider.getItems().addAll(recentlyAddedElementsObs);
        recentlyAddedSlider.setCellFactory(new ElementCellFactory());
        recentlyAddedSlider.setEditable(false);
        recentlyAddedSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
