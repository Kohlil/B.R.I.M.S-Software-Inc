/**
 * Author: Isaiah Kohl
 * Date: 11/10/21
 * Purpose: Controller for Search Results page
 */

package com.levi.melodydirectory;

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

public class SearchController implements Initializable {

    @FXML
    private VBox rootVBox = new VBox();

    @FXML
    private ListView<Generic> resultSlider = new ListView<>();

    /**
     * 
     * @param arg0
     * @param arg1 
     * 
     * Loads search results and displays them in a listview of SearchResultView
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        rootVBox.getChildren().add(0, new HeaderBar(HeaderBar.Page.DEFAULT));
        ObservableList<Generic> resultsObs = FXCollections.observableArrayList();
        
        //should be able to do something like this
        //resultsObs.addAll(db.search(App.getData()));
        
        //temp hard coded results
        Song testSong = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "https://open.spotify.com/track/3KkXRkHbMCARz0aVfEt68P?si=c00edd0981d14652", "SpiderMan Soundtrack", "$2.00", "2:00", "Post Malone");
        resultsObs.add(testSong);
        resultsObs.add(new Artist("Post Malone", "The literal best", "Hip-Hop", "https://open.spotify.com/artist/246dkjvS1zLTtiykXe5h60?si=ouQAFxUQTIiG490PQAzKcw", new ArrayList<>()));
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Sunflower");
        resultsObs.add(new Album("SpiderMan Into the SpiderVerse", "Movie Soundtrack", "Pop", "10/18/18", "https://open.spotify.com/album/35s58BRTGAEWztPo9WqCIs?si=8iHCAbVATSGRFvtr8CJNRw", "Various Artists", songs, "$2.00"));
        //temp hard coded results
        
        
        resultSlider.setCellFactory(new SearchResultCellFactory());
        resultSlider.setEditable(false);
        resultSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        resultSlider.getItems().addAll(resultsObs);
    }
    
    

}

