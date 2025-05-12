package com.github.nissistomppa.ot1kurssityo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;

import javafx.scene.input.MouseEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    private Button luolaskuBut;

    @FXML
    private Button maksettuBut;

    @FXML
    private ListView<String> varaustenlistaLW;


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
    private void varaustentuplaklikkaus() {
        varaustenlistaLW.getSelectionModel().selectedItemProperty().addListener((obs, vanhaValinta, uusiValinta) -> {
            if (uusiValinta != null) {
                taytavarauskentat(uusiValinta);
            }
        });

        varaustenlistaLW.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String valittu = varaustenlistaLW.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    taytavarauskentat(valittu);
                }
            }
        });

        listanmuokkaus();
    }

    @FXML
    private void listanmuokkaus() {
        varaustenlistaLW.setEditable(true);
        varaustenlistaLW.setCellFactory(TextFieldListCell.forListView());
    }

    @FXML
    private void varauksenluonti() {
        varaustenlistaLW.getItems().add(toString());
    }

    private void taytavarauskentat(String tieto) {
        try {
            String[] tietorivi = tieto.split("\n");
            if (tietorivi.length >= 9) {
                etunimi2TF.setText(tietorivi[0].split(":")[1].trim());
                sukunimi2TF.setText(tietorivi[1].split(":")[1].trim());
                puhnro2TF.setText(tietorivi[2].split(":")[1].trim());
                sposti2TF.setText(tietorivi[3].split(":")[1].trim());
                osoite2TF.setText(tietorivi[4].split(":")[1].trim());
                yritys2TF.setText(tietorivi[5].split(":")[1].trim());
                alku2DP.setValue(LocalDate.parse(tietorivi[6].split(":")[1].trim()));
                lopetus2DP.setValue(LocalDate.parse(tietorivi[7].split(":")[1].trim()));
                mokki2CB.setValue(tietorivi[8].split(":")[1].trim());
            }
        } catch (Exception e) {
            System.out.println("Tietojen täytössä havaittu ongelma! Tarkista syöte." + e.getMessage());
        }
    }

    @FXML
    private void muokkauksentallennus() {
        int varauslista = varaustenlistaLW.getSelectionModel().getSelectedIndex();
        if (varauslista >= 0) {
            String uusiTieto = "Etunimi: " + etunimi2TF.getText() + "\n" +
                    "Sukunimi: " + sukunimi2TF.getText() + "\n" +
                    "Puhelinnumero: " + puhnro2TF.getText() + "\n" +
                    "Sähköposti: " + sposti2TF.getText() + "\n" +
                    "Osoite: " + osoite2TF.getText() + "\n" +
                    "Yritys (jos on): " + yritys2TF.getText() + "\n" +
                    "Alku pvm: " + alku2DP.getValue() + "\n" +
                    "Lopetus pvm: " + lopetus2DP.getValue() + "\n" +
                    "Mökki: " + mokki2CB.getValue();
            varaustenlistaLW.getItems().set(varauslista, uusiTieto);
        }
    }

    @FXML
    private void poistavaraus() {
        int varauslista = varaustenlistaLW.getSelectionModel().getSelectedIndex();
        if (varauslista >= 0) {
            varaustenlistaLW.getItems().remove(varauslista);
        } else {
            System.out.println("Poistettavaa varausta ei ole valittu. Tuplaklikkaa uudelleen.");
        }
    }

    @Override
    public String toString() {
        return "Etunimi: " + etunimiTF.getText() + "\n" +
                "Sukunimi: " + sukunimiTF.getText() + "\n" +
                "Puhelinnumero: " + puhnroTF.getText() + "\n" +
                "Sähköposti: " + spostiTF.getText() + "\n" +
                "Osoite: " + osoiteTF.getText() + "\n" +
                "Yritys (jos on): " + yritysTF.getText() + "\n" +
                "Alku pvm: " + alkuDP.getValue() + "\n" +
                "Lopetus pvm: " + lopetusDP.getValue() + "\n" +
                "Mökki: " + mokkiCB.getValue();
    }
    @FXML
    private void luolasku() {
        try {
            String etunimi = etunimiTF.getText();
            String sukunimi = sukunimiTF.getText();
            String sposti = spostiTF.getText();
            String mokki = mokkiCB.getValue() != null ? mokkiCB.getValue().toString() : "";
            double vrkhinta = 0.0;
            long vrkaika = 0;

            if (vrkhintaTF.getText() != null && !vrkhintaTF.getText().isEmpty()) {
                vrkhinta = Double.parseDouble(vrkhintaTF.getText());
            }

            if (alkuDP.getValue() != null && lopetusDP.getValue() != null) {
                vrkaika = ChronoUnit.DAYS.between(alkuDP.getValue(), lopetusDP.getValue());
                if (vrkaika == 0) vrkaika = 1;
            }

            double kokonaishinta = vrkhinta * vrkaika;
            
            String lasku = "Etunimi: " + etunimi + "\n" +
                    "Sukunimi: " + sukunimi + "\n" +
                    "Sähköposti: " + sposti + "\n" +
                    "Mökin nimi: " + mokki + "\n" +
                    "Hinta (vrk): " + vrkhinta + "€" + "\n" +
                    "Aika (vrk): " + vrkaika + "\n" +
                    "Kokonaishinta: " + kokonaishinta + "€";

            laskujenlistaLW.getItems().add(lasku);

        } catch (Exception e) {
            System.out.println("Laskun luonnissa tapahtui virhe: " + e.getMessage());
        }
    }
    @FXML
    private void poistalasku() {
        int varauslista = laskujenlistaLW.getSelectionModel().getSelectedIndex();
        if (varauslista >= 0) {
            laskujenlistaLW.getItems().remove(varauslista);
        } else {
            System.out.println("Poistettavaa laskua ei ole valittu. Tuplaklikkaa uudelleen.");
        }
    }
    @FXML
    private void tallennalasku() {
        if (muokattavienlaskujemaara >= 0) {
            String uusiLasku = "Etunimi: " + etunimi3TF.getText() + "\n" +
                    "Sukunimi: " + sukunimi3TF.getText() + "\n" +
                    "Sähköposti: " + sposti3TF.getText() + "\n" +
                    "Mökin nimi: " + mokinnimi2TF.getText() + "\n" +
                    "Hinta (vrk): " + vrkhinta2TF.getText() + "€\n" +
                    "Aika (vrk): " + vrkaikaTF.getText() + "\n" +
                    "Kokonaishinta: " + kokhintaTF.getText() + "€";

            laskujenlistaLW.getItems().set(muokattavienlaskujemaara, uusiLasku);
            muokattavienlaskujemaara = -1;
        }
    }
    @FXML
    private int muokattavienlaskujemaara;

    @FXML
    private void taytaLaskuKentat(String tieto) {
        try {
            String[] rivit = tieto.split("\n");
            etunimi3TF.setText(rivit[0].split(":")[1].trim());
            sukunimi3TF.setText(rivit[1].split(":")[1].trim());
            sposti3TF.setText(rivit[2].split(":")[1].trim());
            mokinnimi2TF.setText(rivit[3].split(":")[1].trim());
            vrkhinta2TF.setText(rivit[4].split(":")[1].replace("€", "").trim());
            vrkaikaTF.setText(rivit[5].split(":")[1].trim());
            kokhintaTF.setText(rivit[6].split(":")[1].replace("€", "").trim());
        } catch (Exception e) {
            System.out.println("Laskun kenttien täytössä virhe: " + e.getMessage());
        }
    }
    @FXML
    private void laskujentuplaklikkaus() {
        varaustenlistaLW.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String valittu = varaustenlistaLW.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    taytavarauskentat(valittu);
                }
            }
        });
        laskujenlistaLW.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                muokattavienlaskujemaara = laskujenlistaLW.getSelectionModel().getSelectedIndex();
                String valittu = laskujenlistaLW.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    taytaLaskuKentat(valittu);
                }
            }
        });
        listanmuokkaus();
    }

}
