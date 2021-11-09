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
    
    private URL url;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Test");
        //add header bar
        rootVBox.getChildren().add(0, new HeaderBar(true));
        
        //create list for also see section
        ObservableList<Generic> alsoSeeObs = FXCollections.observableArrayList();
        alsoSeeSlider.setCellFactory(new ElementCellFactory());
        alsoSeeSlider.setEditable(false);
        alsoSeeSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //set all fields
        Generic gen = App.getData();
        name.setText(gen.getName());
        genre.setText(gen.getGenre());
        descriptionTextArea.setText(gen.getDescription());
        released.setText(gen.getReleaseDate());
        try {
            url = new URL(gen.geteLink());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            link.setText("INVALID LINK");
        }
        switch (gen.getDataType()) {
            case ARTIST:
                Artist tempArtist = (Artist)gen;
                artist.setVisible(false);
                length.setVisible(false);
                alsoSeeObs.addAll(tempArtist.getAlbums());
                break;
            case ALBUM://create test album to actually test this
                Album tempAlbum = (Album)gen;
                artist.setText(tempAlbum.getArtist());
                albumLabel.setText("Label");
                album.setText(tempAlbum.getLabel());
                alsoSeeSlider.getItems().addAll(tempAlbum.getSongs());
                break;
            case SONG:
                Song tempSong = (Song)gen;
                artist.setText(tempSong.getArtist());
                price.setText(tempSong.getPrice());
                length.setText(tempSong.getSongLength());
                album.setText(tempSong.getAlbum());
                alsoSeeSlider.getItems().addAll();
                break;
        }
        alsoSeeSlider.getItems().addAll(alsoSeeObs);
    }
    
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
