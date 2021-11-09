package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl Date: 10/19/21 Purpose: Allows the GUI to trigger events
 * in the code. Connects home page to back-end of the code
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable {
    
    @FXML
    private VBox rootVBox = new VBox();

    @FXML // fx:id="topRatedSlider"
    private ListView<Generic> topRatedSlider = new ListView<>();

    @FXML // fx:id="recentlyAddedSlider"
    private ListView<Generic> recentlyAddedSlider = new ListView<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add header bar to VBox
        rootVBox.getChildren().add(0, new HeaderBar(false));
        
        ObservableList<Generic> topRatedElementsObs = FXCollections.observableArrayList();
        ObservableList<Generic> recentlyAddedElementsObs = FXCollections.observableArrayList();

        //Element for testing only
        Song testSong = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "https://open.spotify.com/track/3KkXRkHbMCARz0aVfEt68P?si=c00edd0981d14652", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
        topRatedElementsObs.add(testSong);
        recentlyAddedElementsObs.add(new Artist("Post Malone", "The literal best", "Hip-Hop", "N/A", "N/A", new ArrayList<>()));
        
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(testSong);
        recentlyAddedElementsObs.add(new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "https://open.spotify.com/album/35s58BRTGAEWztPo9WqCIs?si=8iHCAbVATSGRFvtr8CJNRw", "Various Artists", songs, "Marvel Studios", "$2.00"));

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
}
