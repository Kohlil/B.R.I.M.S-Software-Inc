/**
 * Author: Isaiah Kohl
 * Date: 11/18/21
 * Purpose: Controls FAQ page, adds header bar
 */

package com.levi.melodydirectory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class FAQController implements Initializable {

    @FXML
    private VBox rootVBox = new VBox();

    /**
     * 
     * @param arg0
     * @param arg1 
     * 
     * adds header bar to top of page
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        rootVBox.getChildren().add(0, new HeaderBar(true));
    }

}
