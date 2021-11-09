package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl
 * Date: 10/15/21
 * Purpose: Start point for application
 */

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Stack;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;
    
    public static HeaderBar.Status STATUS;
    private static Stack stack;

    /**
     * 
     * @param stage
     * @throws IOException 
     * 
     * Entire application begins here
     */
    @Override
    public void start(Stage stage) throws IOException {
        STATUS = HeaderBar.Status.LOGGED_OUT;
        stack = new Stack();
        scene = new Scene(loadFXML("Homepage"), 720, 420); 
        //Song SF = new Song("Sunflower", "From SpiderMan, Into The Spiderverse", "Pop", "10/18/18", "N/A","SpiderMan Soundtrack", "$2.00", "2:00");
        //scene = new Scene(new ElementView(SF, DataTypes.SONG));
        stage.setScene(scene);
        
        stage.setTitle("Melody Directory");
        stage.getIcons().add(new Image(new File("com\\levi\\melodydirectory\\icon.png").toURI().toString()));
        stage.show();
    }

    /**
     * 
     * @param fxml
     * @throws IOException 
     * 
     * Change the FXML file displayed
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * 
     * @param fxml
     * @return
     * @throws IOException 
     * 
     * load an FXML file by file name
     */
    private static Parent loadFXML(String fxml) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void setData(Generic gen) {
        scene.setUserData(gen);
    }
    
    public static Generic getData() {
        return (Generic)scene.getUserData();
    }

    /**
     * 
     * @param args 
     * 
     * standard part of java main class
     */
    public static void main(String[] args) {
        launch();
    }

}