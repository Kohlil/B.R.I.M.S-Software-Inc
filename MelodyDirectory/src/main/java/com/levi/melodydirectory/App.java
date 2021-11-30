package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl 
 * Date: 10/15/21 
 * Purpose: Start point for application
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;

    public static HeaderBar.Status STATUS;
    public static Boolean isRequest = false;
    private static HashSet<Generic> discover = new HashSet<>();
    private static HashSet<Generic> pop = new HashSet<>();

    /**
     *
     * @param stage
     * @throws IOException
     *
     * Entire application begins here
     */
    @Override
    public void start(Stage stage) throws IOException {
        
        try {
            //preload homepage results so that they only have to be loaded once
            DBreturn db = DBreturn.getInstance();
            
            discover.add(db.Search("Pop").get(0));
            discover.add(db.Search("Hip-hop").get(0));
            discover.add(db.Search("Punk").get(0));
            discover.add(db.Search("Rap").get(0));
            discover.add(db.Search("Alternative").get(0));
            pop.addAll(db.Search("Pop").subList(1, 8));
            discover.addAll(db.Search("Pop").subList(1, 8));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        STATUS = HeaderBar.Status.LOGGED_OUT;
        scene = new Scene(loadFXML("Homepage"), 720, 420);
        stage.setScene(scene);

        stage.setTitle("Melody Directory");
        stage.getIcons().add(new Image("com/levi/melodydirectory/icon.png"));
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
     * @param obj Data to be saved
     * 
     * Use this function to allow data to persist between pages
     */
    public static void setData(Object obj) {
        scene.setUserData(obj);
    }

    /**
     * 
     * @return Object that was saved between views
     */
    public static Object getData() {
        return scene.getUserData();
    }

    /**
     * 
     * @return 
     * 
     * get discover elements, loads list one time so that this list doesn't have to be created
     * each time the homepage is loaded
     */
    public static ArrayList<Generic> getDiscover() {
        return (ArrayList<Generic>) (discover.stream().collect(Collectors.toList()));
    }

    /**
     * 
     * @return 
     * 
     * get pop elements, loads list one time so that this list doesn't have to be created
     * each time the homepage is loaded
     */
    public static ArrayList<Generic> getPop() {
        return (ArrayList<Generic>) (pop.stream().collect(Collectors.toList()));
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
