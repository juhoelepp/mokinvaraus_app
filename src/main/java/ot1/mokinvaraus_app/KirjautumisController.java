package ot1.mokinvaraus_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class KirjautumisController {

    private static final String OikeaKayttajatunnus = "mokkikodit";
    private static final String OikeaSalasana = "123456";

    @FXML
    private TextField kayttajatunnus;

    @FXML
    private PasswordField salasana;

    @FXML
    private Label tietokirjautumisesta;

    @FXML
    private Button kirjautumisbt;

    @FXML
    private void vaihdaikkunaan2() {
        String annettuTunnus = kayttajatunnus.getText().trim().toLowerCase();
        String annettuSalasana = salasana.getText().trim();

        if (kayttajatunnus.getText().isEmpty() || salasana.getText().isEmpty()) {
            tietokirjautumisesta.setText("Täytä kaikki kentät ennen kirjautumista");
        } else if (annettuTunnus.equals(OikeaKayttajatunnus) && annettuSalasana.equals(OikeaSalasana)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Kayttoliittyma.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) kirjautumisbt.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            tietokirjautumisesta.setText("Kirjautuminen epäonnistui. Väärä salasana tai käyttäjätunnus");
        }
    }
}
