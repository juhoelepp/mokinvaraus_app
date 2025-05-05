module ohii.mokinvaraus_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens ohii.mokinvaraus_app to javafx.fxml;
    exports ohii.mokinvaraus_app;
}