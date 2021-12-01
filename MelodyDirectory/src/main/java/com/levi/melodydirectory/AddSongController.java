/**
 * Author: Isaiah Kohl
 * Date: 11/29/21
 * Purpose: Page for creating song/album/artist requests and sending them to requests db
 */

package com.levi.melodydirectory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class AddSongController implements Initializable {

    private DBreturn db = DBreturn.getInstance();//db access
    private ObservableList<Generic> albumObs = FXCollections.observableArrayList();//list for albums autifill
    private ObservableList<Generic> artistObs = FXCollections.observableArrayList();//list for artist autofill
    private HashSet<Generic> albumList = new HashSet();//hash for album autofill to prevent dup results
    private HashSet<Generic> artistList = new HashSet();//hash for artist autofill to prevent dup results

    //FXML fields
    @FXML
    private VBox rootVBox = new VBox();

    @FXML
    private ListView<Generic> albumAutofill = new ListView<>();

    @FXML
    private ListView<Generic> artistAutofill = new ListView();

    @FXML
    private TextField songName = new TextField();

    @FXML
    private TextField songReleased = new TextField();

    @FXML
    private TextField songGenre = new TextField();

    @FXML
    private TextField songLength = new TextField();

    @FXML
    private TextArea songDescription = new TextArea();

    @FXML
    private TextField songLink = new TextField();

    @FXML
    private TextField songPrice = new TextField();

    @FXML
    private TextField albumName = new TextField();

    @FXML
    private TextField albumReleased = new TextField();

    @FXML
    private TextArea albumDescription = new TextArea();

    @FXML
    private TextField albumLink = new TextField();

    @FXML
    private TextField albumPrice = new TextField();

    @FXML
    private TextField artistName = new TextField();

    @FXML
    private TextArea artistDescription = new TextArea();

    @FXML
    private TextField artistLink = new TextField();

    /**
     * 
     * @param arg0
     * @param arg1 
     * 
     * Adds header bar to top of page without the add songs button
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        rootVBox.getChildren().add(0, new HeaderBar(HeaderBar.Page.ADDSONG));
    }

    /**
     * 
     * @param event 
     * 
     * Updates album autofill when a key is typed, should be changed to key pressed
     * so that it also updates when backspace is pressed
     */
    @FXML
    void albumKeyTyped(KeyEvent event) {
        albumObs.clear();
        albumList.clear();
        albumAutofill.getItems().clear();//clear current results
        try {
            albumList.addAll(db.Search(albumName.getText()));//get updated results
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Generic gen : albumList) {
            if ((gen.getDataType() == Generic.DataTypes.ALBUM)) {//only show albums
                albumObs.add((Album) gen);
            }
        }

        //set cell factory for listview
        albumAutofill.setCellFactory(new AutoFillCellFactory());
        albumAutofill.setEditable(false);
        albumAutofill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        albumAutofill.getItems().addAll(albumObs);
    }

    /**
     * 
     * @param event 
     * 
     * Updates artist autofill when a key is typed, should be changed to key pressed
     * so that it also updates when backspace is pressed
     */
    @FXML
    void artistKeyTyped(KeyEvent event) {
        artistObs.clear();
        artistList.clear();
        artistAutofill.getItems().clear();//clear old results
        try {
            artistList.addAll(db.Search(artistName.getText()));//get new results
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Generic gen : artistList) {
            if ((gen.getDataType() == Generic.DataTypes.ARTIST)) {//only show artists
                artistObs.add((Artist) gen);
            }
        }

        //set cell factory for list view
        artistAutofill.setCellFactory(new AutoFillCellFactory());
        artistAutofill.setEditable(false);
        artistAutofill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        artistAutofill.getItems().addAll(artistObs);

    }
    
    /**
     * 
     * @param event 
     * 
     * sends request to DB to be stored for admin approval
     */
    @FXML
    void sendRequestPressed(ActionEvent event) throws IOException {
        try {
            db.setRequests(true);//set db to requests mode
            db.addSong(albumName.getText(), artistName.getText(),//create request
                    songName.getText(), songGenre.getText(),
                    songDescription.getText(), songLink.getText(),
                    songReleased.getText(), songPrice.getText(),
                    songLength.getText(), songGenre.getText(),
                    albumDescription.getText(), albumLink.getText(),
                    albumReleased.getText(), albumPrice.getText(),
                    songGenre.getText(), artistDescription.getText(),
                    artistLink.getText());
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(5);
        }
        finally {
            db.setRequests(false);//set db back to normal approved data
        }
        App.setRoot("Homepage");//return home
    }

    //Setters to allow AutoFillView to set fields
    public void setAlbumName(String albumName) {
        this.albumName.setText(albumName);
    }

    public void setAlbumReleased(String albumReleased) {
        this.albumReleased.setText(albumReleased);
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription.setText(albumDescription);
    }

    public void setAlbumLink(String albumLink) {
        this.albumLink.setText(albumLink);
    }

    public void setAlbumPrice(String albumPrice) {
        this.albumPrice.setText(albumPrice);
    }

    public void setArtistName(String artistName) {
        this.artistName.setText(artistName);
    }

    public void setArtistDescription(String artistDescription) {
        this.artistDescription.setText(artistDescription);
    }

    public void setArtistLink(String artistLink) {
        this.artistLink.setText(artistLink);
    }

    //Inner class for defining objects that are displayed as autofill results
    private class AutoFillView extends StackPane implements Initializable {

        Generic gen;//object being displayed

        @FXML
        private Label name = new Label();

        //default constuctor
        public AutoFillView(Generic gen) {
            super();
            this.gen = gen;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AutoFillView.fxml"));
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
         * Set label of object to name
         */
        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            name.setText(gen.getName());
        }

        /**
         *
         * @param event
         *
         * When user clicks this AutoFillView, sets fields
         */
        @FXML
        void mouseClicked(ActionEvent event) {

            //Set fields for whatever type gen is
            if (gen.getDataType() == Generic.DataTypes.ALBUM) {
                Album temp = (Album) gen;
                setAlbumDescription(temp.getDescription());
                setAlbumLink(temp.geteLink());
                setAlbumName(temp.getName());
                setAlbumPrice(temp.getPrice());
                setAlbumReleased(temp.getReleaseDate());
            } else {
                Artist temp = (Artist) gen;
                setArtistDescription(temp.getDescription());
                setArtistLink(temp.geteLink());
                setArtistName(temp.getName());
            }
        }
    }

    /**
     * Factory for creating autofill objects and populating the list views
     */
    private class AutoFillCellFactory implements Callback<ListView<Generic>, ListCell<Generic>> {

        @Override
        public ListCell<Generic> call(ListView<Generic> param) {
            return new ListCell<>() {
                @Override
                public void updateItem(Generic gen, boolean empty) {
                    super.updateItem(gen, empty);
                    if (empty) {//don't populate list
                        setText(null);
                        setGraphic(null);
                    } else if (gen != null) {//populate list with AutoFillViews
                        setText(null);
                        setGraphic(new AddSongController.AutoFillView(gen));
                    } else {//populate list with empty cells
                        setText("null");
                        setGraphic(null);
                    }
                    this.setEditable(false);
                }
            };
        }
    }

}
