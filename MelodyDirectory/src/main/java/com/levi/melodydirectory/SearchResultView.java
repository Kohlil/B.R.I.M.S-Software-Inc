/**
 * Author: Isaiah Kohl
 * Date: 11/17/21
 * Purpose: Controller and actual object for searchResultView
 */
package com.levi.melodydirectory;

import com.levi.melodydirectory.Generic.DataTypes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author isaia
 */
public class SearchResultView extends StackPane implements Initializable {
    private Generic gen;//the object this represents

    //FXML fields
    @FXML
    private HBox searchResultView = new HBox();
    
    @FXML
    private HBox ratingHBox = new HBox();

    @FXML
    private Label name = new Label();

    @FXML
    private Label genre = new Label();

    @FXML
    private Label released = new Label();

    @FXML
    private Label rating = new Label();
    
    @FXML
    private Label ratingLabel = new Label();
    
    @FXML
    private Label releasedLabel = new Label();

    /**
     * 
     * @param gen Object represented
     * @param dataType DataType of object to differentiate between types
     * 
     * Constructor for view
     */
    public SearchResultView(Generic gen, DataTypes dataType) {
        super();
        this.gen = gen;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SearchResultView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     * @param arg0
     * @param arg1 
     * 
     * Configures and sets all fields depending on dataType
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(gen.getName());
        genre.setText(gen.getGenre());
        
        //add custom fields depending on datatype
        switch (gen.getDataType()) {
            case ARTIST://artist only displays name and genre
                ratingHBox.setVisible(false);
                released.setVisible(false);
                releasedLabel.setVisible(false);
                released.setDisable(true);
                releasedLabel.setDisable(true);
                break;
            case ALBUM://set all fields to make sense for an album
                Album tempAlbum = (Album)gen;
                released.setText(tempAlbum.getReleaseDate());
                rating.setText(tempAlbum.getArtist());
                ratingLabel.setText("Artist:");
                searchResultView.getChildren().remove(3);
                searchResultView.getChildren().add(2, ratingHBox);
                
                break;
            case SONG://set all fields to make sense for a song
                Song tempSong = (Song)gen;
                released.setText(tempSong.getReleaseDate());
                rating.setText(tempSong.getArtist());
                ratingLabel.setText("Artist:");
                searchResultView.getChildren().remove(3);
                searchResultView.getChildren().add(2, ratingHBox);
                break; 
        }
    }
    
    /**
     * 
     * @param event 
     * 
     * When the element is clicked
     */
    @FXML
    void mouseClicked(ActionEvent event) {
        try {
            //Load ElementViewPage here
            App.setData(gen);
            App.setRoot("View");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
