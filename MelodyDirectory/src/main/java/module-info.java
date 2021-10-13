module com.levi.melodydirectory {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.levi.melodydirectory to javafx.fxml;
    exports com.levi.melodydirectory;
}