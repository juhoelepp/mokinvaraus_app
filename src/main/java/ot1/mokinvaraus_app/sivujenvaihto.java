package ot1.mokinvaraus_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sivujenvaihto {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void vaihdaikkunaan1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Kansilehti.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void vaihdaikkunaan2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Kayttoliittyma.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
