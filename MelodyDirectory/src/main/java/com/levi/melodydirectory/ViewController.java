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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author isaia
 */
public class ViewController implements Initializable {

    //FXML fields
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
    private TextArea descriptionTextArea = new TextArea();

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

    @FXML
    private HBox adminBox = new HBox();
    
    @FXML
    private Button approveButton = new Button();
    
    @FXML
    private Button denyButton = new Button();

    private URL url;//url for 3rd party link to element
    Generic gen = (Generic) App.getData();

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
        rootVBox.getChildren().add(0, new HeaderBar(HeaderBar.Page.DEFAULT));

        //create list for also see section
        ObservableList<Generic> alsoSeeObs = FXCollections.observableArrayList();
        alsoSeeSlider.setCellFactory(new ElementCellFactory());
        alsoSeeSlider.setEditable(false);
        alsoSeeSlider.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //show adminBox or no?
        if (HeaderBar.isAdmin && gen.getDataType() == Generic.DataTypes.SONG && App.isRequest) {
            adminBox.setVisible(true);
            adminBox.setDisable(false);
            approveButton.setDisable(false);
            approveButton.setVisible(true);
            denyButton.setDisable(false);
            denyButton.setVisible(true);
            alsoSeeSlider.setDisable(true);
            alsoSeeSlider.setVisible(false);
        }
        else {
            adminBox.setVisible(false);
            adminBox.setDisable(true);
            approveButton.setDisable(true);
            approveButton.setVisible(false);
            denyButton.setDisable(true);
            denyButton.setVisible(false);
            App.isRequest = false;
        }
        //set all fields
        name.setText(gen.getName());
        genre.setText(gen.getGenre());
        descriptionTextArea.setText(gen.getDescription());
        released.setText(gen.getReleaseDate());
        DBreturn db = null;
        HashSet<Generic> alsoSee = new HashSet<>();//hash to prevent dup elements in alsoSee
        try {
            db = DBreturn.getInstance();
            url = new URL(gen.geteLink());//attempt to create a link
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            link.setText("INVALID LINK");//link is not valid
        }
        switch (gen.getDataType()) {
            case ARTIST://set all fields to make sense for an artist
                Artist tempArtist = (Artist) gen;
                artist.setVisible(false);
                length.setVisible(false);
                price.setVisible(false);
                lengthLabel.setVisible(false);
                releasedLabel.setVisible(false);
                released.setVisible(false);
                album.setVisible(false);
                albumLabel.setVisible(false);
                try {//load results for AlsoSee section
                    alsoSee.addAll(db.Search(tempArtist.getName()));
                    alsoSee.addAll(db.Search(tempArtist.getGenre()));
                    alsoSee.remove(tempArtist);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.exit(5);
                }
                break;

            case ALBUM://set all fields to make sense for an album
                Album tempAlbum = (Album) gen;
                artist.setText(tempAlbum.getArtist());
                albumLabel.setVisible(false);
                album.setVisible(false);
                price.setText(tempAlbum.getPrice());
                lengthLabel.setVisible(false);
                length.setVisible(false);
                try {//load results for AlsoSee section
                    alsoSee.addAll(db.Search(tempAlbum.getName()));
                    alsoSee.addAll(db.Search(tempAlbum.getGenre()));
                    alsoSee.remove(tempAlbum);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.exit(5);
                }
                break;
            case SONG://set all fields to make sense for a song
                Song tempSong = (Song) gen;
                artist.setText(tempSong.getArtist());
                price.setText(tempSong.getPrice());
                length.setText(tempSong.getSongLength());
                album.setText(tempSong.getAlbum());
                try {//load results for AlsoSee section
                    alsoSee.addAll(db.Search(tempSong.getName()));
                    alsoSee.addAll(db.Search(tempSong.getGenre()));
                    alsoSee.remove(tempSong);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.exit(5);
                }
                break;
        }
        //make sure alsoSee isn't larger than 10 elements
        if (alsoSee.size() > 10) {
            ArrayList<Generic> temp = new ArrayList<>();
            temp.addAll(alsoSee);
            alsoSee.clear();
            alsoSee.addAll(temp.subList(0, 9));
        }
        alsoSeeSlider.getItems().addAll(alsoSee);//add all items to visible slider
    }

    /**
     * 
     * @param event
     * @throws IOException 
     * 
     * Launches web browser to 3rd party link, only works for windows ten users
     */
    @FXML
    void linkPressed(ActionEvent event) throws IOException {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + "\"" + url + "\"");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * 
     * @param event 
     * 
     * adds song/album/artist to db
     */
    @FXML
    void approveButtonPressed(ActionEvent event) throws IOException, SQLException {
        DBreturn db = DBreturn.getInstance();//get db access
        Song s = (Song)gen;
        db.setRequests(true);//set db to requests mode
        Album al = db.getSpecificAlbum(s.getAlbum());//does not work
        Artist ar = db.getSpecificArtist(s.getArtist());//does not work
        db.setRequests(false);//set db back to normal approved mode
        db.addSong(al.getName(), ar.getName(), s.getName(), s.getGenre(), s.getDescription(), 
                s.geteLink(), s.getReleaseDate(), s.getPrice(), s.getSongLength(), 
                al.getName(), al.getDescription(), al.geteLink(), al.getReleaseDate(), 
                al.getPrice(), ar.getGenre(), ar.getDescription(), ar.geteLink());
        
        //delete request from db and load requests page again
        denyButtonPressed(new ActionEvent());
    }

    /**
     * 
     * @param event 
     * 
     * deletes request from db and load search requests again
     */
    @FXML
    void denyButtonPressed(ActionEvent event) throws SQLException, IOException {
        DBreturn db = DBreturn.getInstance();
        db.setRequests(true);
        db.delete(gen.getName());
        db.setRequests(false);
        App.setData("12345test");//really bad way of having a special string that loads all requests
        App.setRoot("SearchResults");//instead of performing search
    }
}
