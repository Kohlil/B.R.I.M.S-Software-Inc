/**
 * Author: Isaiah Kohl
 * Date: 11/10/21
 * Purpose: Controller for Search Results page
 */

package com.levi.melodydirectory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        try {
            DBreturn db = DBreturn.getInstance();
            if (((String)App.getData()).equals("12345test")) {
                db.setRequests(true);
                resultsObs.addAll(db.getAllSongs());
                db.setRequests(false);
                App.isRequest = true;
            } else {
                App.isRequest = false;
                resultsObs.addAll(db.Search((String)App.getData()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        resultSlider.setCellFactory(new SearchResultCellFactory());
        resultSlider.setEditable(false);
        resultSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        resultSlider.getItems().addAll(resultsObs);
    }
    
    

}

