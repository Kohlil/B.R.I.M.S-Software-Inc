/* 
 * Author: Isaiah Kohl
 * Date: 11/7/21
 * Purpose: Fills view page with accurate information regarding each
 * song/artist/album
 */
package com.levi.melodydirectory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author isaia
 */
public class ViewController implements Initializable {
    @FXML
    private VBox rootVBox = new VBox();

    @FXML
    private Label name = new Label();

    @FXML
    private Label artist = new Label();

    @FXML
    private Label genre = new Label();

    @FXML
    private Label released = new Label();

    @FXML
    private Label album = new Label();

    @FXML
    private Label length = new Label();

    @FXML
    private TextField descriptionTextArea = new TextField();

    @FXML
    private Hyperlink link = new Hyperlink();

    @FXML
    private Label price = new Label();

    @FXML
    private ListView<Generic> alsoSeeSlider = new ListView<>();
    
    @FXML
    private Label albumLabel = new Label();
    
    @FXML
    private Label releasedLabel = new Label();
    
     @FXML
    private Label lengthLabel = new Label();
    
    private URL url;//url for 3rd party link to element
    
    
    /**
     * 
     * @param arg0
     * @param arg1 
     * 
     * Sets all fields depending on what type of element is being displayed and
     * fills also see slider with close matches and similarities
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //add header bar
        rootVBox.getChildren().add(0, new HeaderBar(true));
        
        //create list for also see section
        ObservableList<Generic> alsoSeeObs = FXCollections.observableArrayList();
        alsoSeeSlider.setCellFactory(new ElementCellFactory());
        alsoSeeSlider.setEditable(false);
        alsoSeeSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //set all fields
        Generic gen = (Generic)App.getData();
        name.setText(gen.getName());
        genre.setText(gen.getGenre());
        descriptionTextArea.setText(gen.getDescription());
        released.setText(gen.getReleaseDate());
        try {
            url = new URL(gen.geteLink());//attempt to create a link
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            link.setText("INVALID LINK");//link is not valid
        }
        switch (gen.getDataType()) {
            case ARTIST://set all fields to make sense for an artist
                Artist tempArtist = (Artist)gen;
                artist.setVisible(false);
                length.setVisible(false);
                price.setVisible(false);
                lengthLabel.setVisible(false);
                releasedLabel.setVisible(false);
                released.setVisible(false);
                album.setVisible(false);
                albumLabel.setVisible(false);
                // waiting on a way to add items to alsoSeeSlider.getItems().addAll(tempArtist.getAlbums());
                //this will allow for the display of albums an artist has
                break;
            case ALBUM://set all fields to make sense for an album
                Album tempAlbum = (Album)gen;
                artist.setText(tempAlbum.getArtist());
                albumLabel.setVisible(false);
                album.setVisible(false);
                price.setText(tempAlbum.getPrice());
                lengthLabel.setVisible(false);
                length.setVisible(false);
                // waiting on a way to add items to alsoSeeSlider.getItems().addAll(tempAlbum.getSongs());
                //this will allow for the display of songs in albums
                break;
            case SONG://set all fields to make sense for a song
                Song tempSong = (Song)gen;
                artist.setText(tempSong.getArtist());
                price.setText(tempSong.getPrice());
                length.setText(tempSong.getSongLength());
                album.setText(tempSong.getAlbum());
                // waiting for DB access to allow for alsoSeeSlider.getItems().addAll();
                break;  
        }
        alsoSeeSlider.getItems().addAll(alsoSeeObs);//add all items to visible slider
    }
    
    
    //launches web browser to 3rd party link
    @FXML
    void linkPressed(ActionEvent event) throws IOException {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + "\"" + url + "\"");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
