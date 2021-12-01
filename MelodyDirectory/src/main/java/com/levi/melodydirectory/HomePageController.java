package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl 
 * Date: 10/19/21 
 * Purpose: Allows the GUI to trigger events
 * in the code. Connects home page to back-end of the code
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable {
    
    //FXML fields
    @FXML
    private VBox root = new VBox();

    @FXML // fx:id="topRatedSlider"
    private ListView<Generic> topRatedSlider = new ListView<>();

    @FXML // fx:id="recentlyAddedSlider"
    private ListView<Generic> recentlyAddedSlider = new ListView<>();

    /**
     * 
     * @param url
     * @param rb
     * 
     * Loads recentlyAdded slider and topRated slider
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add header bar to VBox
        root.getChildren().add(0, new HeaderBar(HeaderBar.Page.HOMEPAGE));
        

        //Set cell factories to have pretty heckn' dope view of results
        topRatedSlider.setCellFactory(new ElementCellFactory());
        topRatedSlider.getItems().addAll(App.getDiscover());
        topRatedSlider.setEditable(false);
        topRatedSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        recentlyAddedSlider.getItems().addAll(App.getPop());
        recentlyAddedSlider.setCellFactory(new ElementCellFactory());
        recentlyAddedSlider.setEditable(false);
        recentlyAddedSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
