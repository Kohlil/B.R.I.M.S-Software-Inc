package com.levi.melodydirectory;

/**
 * Author: Isaiah Kohl Date: 10/15/21 Purpose: Start point for application
 */
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    public static void setData(Object obj) {
        scene.setUserData(obj);
    }

    public static Object getData() {
        return scene.getUserData();
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
