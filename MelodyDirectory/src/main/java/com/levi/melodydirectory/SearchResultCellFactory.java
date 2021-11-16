/**
 * Author: Isaiah Kohl
 * Date: 11/12/21
 * Purpose: Creates GUI components for search results
 */

package com.levi.melodydirectory;

import com.levi.melodydirectory.Generic.DataTypes;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SearchResultCellFactory implements Callback<ListView<Generic>, ListCell<Generic>> {

    @Override
    public ListCell<Generic> call(ListView<Generic> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Generic gen, boolean empty) {
                super.updateItem(gen, empty);
                if (empty) {//don't populate list
                    setText(null);
                    setGraphic(null);
                } 
                else if (gen != null) {//populate list with SearchResultViews
                    setText(null);
                    setGraphic(new SearchResultView(gen, DataTypes.SONG)); 
                } 
                else {//populate list with empty cells
                    setText("null");
                    setGraphic(null);
                }
                this.setEditable(false);
            }
        };
    }
}
