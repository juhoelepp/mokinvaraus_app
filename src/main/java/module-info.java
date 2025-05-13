module ohii.mokinvaraus_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens ot1.mokinvaraus_app to javafx.fxml;
    exports ot1.mokinvaraus_app;
}