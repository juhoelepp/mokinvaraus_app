package com.github.nissistomppa.ot1kurssityo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;

import java.time.LocalDate;

public class HelloController {
    @FXML
    private Tab Tab1, Tab2, Tab3, Tab4, Tab5;

    @FXML
    private TextField etunimiTF;

    @FXML
    private TextField sukunimiTF;

    @FXML
    private TextField puhnroTF;

    @FXML
    private TextField spostiTF;

    @FXML
    private TextField osoiteTF;

    @FXML
    private TextField yritysTF;

    @FXML
    private TextField etunimi2TF;

    @FXML
    private TextField sukunimi2TF;

    @FXML
    private TextField puhnro2TF;

    @FXML
    private TextField sposti2TF;

    @FXML
    private TextField osoite2TF;

    @FXML
    private TextField yritys2TF;

    @FXML
    private TextField mokinnimiTF;

    @FXML
    private TextField vrkhintaTF;

    @FXML
    private TextField sijaintiTF;

    @FXML
    private TextField hlomaaraTF;

    @FXML
    private TextField neliomaaraTF;

    @FXML
    private TextArea mukavuudetTF;

    @FXML
    private TextArea kuvausTF;

    @FXML
    private TextField etunimi3TF;

    @FXML
    private TextField sukunimi3TF;

    @FXML
    private TextField sposti3TF;

    @FXML
    private TextField mokinnimi2TF;

    @FXML
    private TextField vrkhinta2TF;

    @FXML
    private TextField vrkaikaTF;

    @FXML
    private TextField kokhintaTF;

    @FXML
    private DatePicker alkuDP;

    @FXML
    private DatePicker alku2DP;

    @FXML
    private DatePicker lopetusDP;

    @FXML
    private DatePicker lopetus2DP;

    @FXML
    private Button luovarausBut;

    @FXML
    private Button peruutavarausBut;

    @FXML
    private Button muokkaamokkiBut;

    @FXML
    private Button tallennamokkiBut;

    @FXML
    private Button muokkaalaskuBut;

    @FXML
    private Button tallennalaskuBut;

    @FXML
    private Button poistalaskuBut;

    @FXML
    private ListView<String> varaustenlistaLW;

    @FXML
    private void listanmuokkaus() {
        varaustenlistaLW.setEditable(true);
        varaustenlistaLW.setCellFactory(TextFieldListCell.forListView());
    }

    @FXML
    private void Varaus() {
        String etunimi2TF, sukunimi2TF, puhnro2TF, osoite2TF, yritys2TF, mokki2CB;
        LocalDate alku2DP, lopetus2DP;
    }

    @Override
    public String toString() {
        return "Etunimi: " + etunimiTF.getText() + '\n' +
                "Sukunimi: " + sukunimiTF.getText() + '\n' +
                "Puhelinnumero: " + puhnroTF.getText() + '\n' +
                "Sähköposti: " + spostiTF.getText() + '\n' +
                "Osoite: " + osoiteTF.getText() + '\n' +
                "Yritys (jos on): " + yritysTF.getText() + '\n' +
                "Alku pvm: " + alkuDP.getValue() + '\n' +
                "Lopetus pvm: " + lopetusDP.getValue() + '\n' +
                "Mökki: " + mokkiCB.getValue();
    }

    @FXML
    private ListView<String> mokkilistaLW;


    @FXML
    private ListView<String> laskujenlistaLW;

    @FXML
    private ListView<String> raportointilistaLW;

    @FXML
    private ComboBox mokkiCB;

    @FXML
    private ComboBox mokki2CB;

    @FXML
    private void varauksenluonti() {
        varaustenlistaLW.getItems().add(toString());
    }

    private void taytaKentat(String tieto) {
        // Oletetaan että tiedot eroteltu | -merkillä
        String[] osat = tieto.split("\\|");
        if (osat.length >= 5) {
            etunimi2TF.setText(osat[0].trim().split(" ")[0]);
            sukunimi2TF.setText(osat[0].trim().split(" ")[1]);
            puhnro2TF.setText(osat[1].trim());
            sposti2TF.setText(osat[2].trim());
            osoite2TF.setText(osat[3].trim());
            yritys2TF.setText(osat[4].trim());
        }
    }

    @FXML
    private void muokkauksentallennus() {
        int indeksi = varaustenlistaLW.getSelectionModel().getSelectedIndex();
        if (indeksi >= 0) {
            String uusiTieto = "Etunimi: " + etunimi2TF.getText() + "\n" +
                    "Sukunimi: " + sukunimi2TF.getText() + "\n" +
                    "Puhelinnumero: " + puhnro2TF.getText() + "\n" +
                    "Sähköposti: " + sposti2TF.getText() + "\n" +
                    "Osoite: " + osoite2TF.getText() + "\n" +
                    "Yritys (jos on): " + yritys2TF.getText() + "\n" +
                    "Alku pvm: " + alku2DP.getValue() + "\n" +
                    "Lopetus pvm: " + lopetus2DP.getValue() + "\n" +
                    "Mökki: " + mokki2CB.getValue();
            varaustenlistaLW.getItems().set(indeksi, uusiTieto);
        }


        varaustenlistaLW.getSelectionModel().selectedItemProperty().addListener((obs, vanhaValinta, uusiValinta) -> {
            if (uusiValinta != null) {
                taytaKentat(uusiValinta);
            }
        });
    }
}
