module com.levi.melodydirectory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.junit.jupiter.api;

    opens com.levi.melodydirectory to javafx.fxml;
    exports com.levi.melodydirectory;
}