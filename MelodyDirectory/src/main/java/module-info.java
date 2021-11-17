module com.levi.melodydirectory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.levi.melodydirectory to javafx.fxml;
    exports com.levi.melodydirectory;
}