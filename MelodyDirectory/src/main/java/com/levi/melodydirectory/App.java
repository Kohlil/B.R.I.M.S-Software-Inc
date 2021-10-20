package com.levi.melodydirectory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Author: Isaiah Kohl
 * Date: 10/15/21
 * Purpose: Start point for application
 */
public class App extends Application {

    private static Scene scene;

    /**
     * 
     * @param stage
     * @throws IOException 
     * 
     * Entire application begins here
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Homepage"), 720, 420); 
        stage.setScene(scene);
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