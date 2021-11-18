/**
 * Author: Isaiah Kohl
 * Date: 11/4/21
 * Purpose: Defines the GUI actions of the ElementView for sliders
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author isaia
 */
public class ElementView extends StackPane implements Initializable {

    private Generic gen;//Object to be shown

    @FXML
    private VBox elementView;

    @FXML
    private Label name = new Label();

    @FXML
    private Label genre = new Label();

    @FXML
    private Label released = new Label();

    @FXML
    private Label rating = new Label();

    /**
     * 
     * @param gen
     * @param dataType 
     * 
     * Create object
     */
    public ElementView(Generic gen, DataTypes dataType) {
        super();
        this.gen = gen;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ElementView.fxml"));
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
     * set fields
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(gen.getName());
        genre.setText(gen.getGenre());
        released.setText(gen.getReleaseDate());
        rating.setText(gen.getName());
    }
    
    /**
     * 
     * @param event 
     * 
     * When user clicks this ElementView, loads View page
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
