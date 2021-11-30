package com.levi.melodydirectory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private DBreturn db = DBreturn.getInstance();
    private ObservableList<Generic> albumObs = FXCollections.observableArrayList();
    private ObservableList<Generic> artistObs = FXCollections.observableArrayList();
    private HashSet<Generic> albumList = new HashSet();
    private HashSet<Generic> artistList = new HashSet();

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        rootVBox.getChildren().add(0, new HeaderBar(HeaderBar.Page.ADDSONG));
    }

    @FXML
    void albumKeyTyped(KeyEvent event) {
        albumObs.clear();
        albumList.clear();
        albumAutofill.getItems().clear();
        try {
            albumList.addAll(db.Search(albumName.getText()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Generic gen : albumList) {
            if ((gen.getDataType() == Generic.DataTypes.ALBUM)) {
                albumObs.add((Album) gen);
            }
        }

        albumAutofill.setCellFactory(new AutoFillCellFactory());
        albumAutofill.setEditable(false);
        albumAutofill.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        albumAutofill.getItems().addAll(albumObs);
    }

    @FXML
    void artistKeyTyped(KeyEvent event) {
        artistObs.clear();
        artistList.clear();
        artistAutofill.getItems().clear();
        try {
            artistList.addAll(db.Search(artistName.getText()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (Generic gen : artistList) {
            if ((gen.getDataType() == Generic.DataTypes.ARTIST)) {
                artistObs.add((Artist) gen);
            }
        }

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
            db.setRequests(true);
            db.addSong(albumName.getText(), artistName.getText(),
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
            db.setRequests(false);
        }
        App.setRoot("Homepage");
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

    private class AutoFillView extends StackPane implements Initializable {

        Generic gen;

        @FXML
        private Label name = new Label();

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
                    } else if (gen != null) {//populate list with SearchResultViews
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
