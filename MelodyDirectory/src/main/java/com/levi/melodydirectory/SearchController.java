/**
 * Author: Isaiah Kohl
 * Date: 11/10/21
 * Purpose: Controller for Search Results page
 */

package com.levi.melodydirectory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

public class SearchController implements Initializable {

    //FXML fields
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
        rootVBox.getChildren().add(0, new HeaderBar(HeaderBar.Page.DEFAULT));//add header bar
        ObservableList<Generic> resultsObs = FXCollections.observableArrayList();//results list
        
        try {
            DBreturn db = DBreturn.getInstance();//get db access
            if (((String)App.getData()).equals("12345test")) {
                //IF the string above is equal to 12345test, load requests page,
                //otherwise search db for stored string and show results
                db.setRequests(true);
                resultsObs.addAll(db.getAllSongs());
                db.setRequests(false);
                App.isRequest = true;
            } else {//search string
                App.isRequest = false;
                resultsObs.addAll(db.Search((String)App.getData()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        //set cell factory
        resultSlider.setCellFactory(new SearchResultCellFactory());
        resultSlider.setEditable(false);
        resultSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        resultSlider.getItems().addAll(resultsObs);
    }
    
    

}

