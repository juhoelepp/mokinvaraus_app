package ot1.mokinvaraus_app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Kayttoliittyma implements Initializable {
    Tietokanta tietokanta;
    ArrayList<Mokki> mokit;
    ArrayList<Varaus> varaukset;
    ArrayList<Asiakas> asiakkaat;

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
    private TextField hlomaaraTF2;

    @FXML
    private TextArea toiveetTF;

    @FXML
    private TextField neliomaaraTF;

    @FXML
    private TextArea mukavuudetTA;

    @FXML
    private TextArea kuvausTA;

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
    private ComboBox<String> mokkiCB;

    @FXML
    private ComboBox<String> mokki2CB;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tietokanta = new Tietokanta();
        mokit = tietokanta.haeMokit();
        varaukset = tietokanta.haeVaraukset();
        asiakkaat = tietokanta.haeAsiakkaat();

        for (Mokki mokki : mokit) {
            mokkiCB.getItems().add(mokki.getNimi());
            mokki2CB.getItems().add(mokki.getNimi());

            mokkilistaLW.getItems().add(mokki.getNimi());
        }
    }

    @FXML
    private void varaustentuplaklikkaus(MouseEvent event) {
        if (event.getClickCount() == 2) {
            muokattavienlaskujemaara = laskujenlistaLW.getSelectionModel().getSelectedIndex();
            String valittu = varaustenlistaLW.getSelectionModel().getSelectedItem();
            if (valittu != null) {
                taytavarauskentat(valittu);
            }
        }

        listanmuokkaus();
    }

    @FXML
    private void mokkientuplaklikkaus(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String valittu = mokkilistaLW.getSelectionModel().getSelectedItem();
            if (valittu != null) {
                for (Mokki mokki : mokit) {
                    if (mokki.getNimi().equals(valittu)) {
                        taytamokkikentat(mokki);
                    }
                }
            }
        }
    }

    @FXML
    private void listanmuokkaus() {
    }

    private int luoAsiakas() {
        Asiakas uusi_asiakas = new Asiakas();
        int asiakas_id = asiakkaat.size() + 1;
        uusi_asiakas.setAsiakasID(asiakas_id);
        uusi_asiakas.setNimi(etunimiTF.getText() + " " + sukunimiTF.getText());
        uusi_asiakas.setPuhelinnumero(puhnroTF.getText());
        uusi_asiakas.setSahkoposti(spostiTF.getText());
        uusi_asiakas.setOsoite(osoiteTF.getText());
        uusi_asiakas.setYritys(yritysTF.getText());

        asiakkaat.add(uusi_asiakas);
        tietokanta.lisaaAsiakas(uusi_asiakas);

        return asiakas_id;
    }

    @FXML
    private void luovaraus() {
        try {
            Varaus uusi_varaus = new Varaus();
            uusi_varaus.setVarausID(varaukset.size() + 1);
            LocalDate aloitus_pvm = alkuDP.getValue();
            LocalDate lopetus_pvm = lopetusDP.getValue();
            uusi_varaus.setKestoPaivia((int) ChronoUnit.DAYS.between(aloitus_pvm, lopetus_pvm));
            uusi_varaus.setAsiakasID(luoAsiakas());
            String varattava_mokki = mokkiCB.getValue();
            for (Mokki mokki : mokit) {
                if (mokki.getNimi().equals(varattava_mokki)) {
                    uusi_varaus.setMokkiID(mokki.getMokkiID());
                }
            }
            uusi_varaus.setHenkilomaara(Integer.parseInt(hlomaaraTF.getText()));
            uusi_varaus.setToiveet(toiveetTF.getText());
            uusi_varaus.setAsiakaspalvelijaID(1);
            uusi_varaus.setAloitusPvm(Date.valueOf(alkuDP.getValue()));
            uusi_varaus.setLopetusPvm(Date.valueOf(lopetusDP.getValue()));
            uusi_varaus.setVarauksenTila("Varattu.");

            varaukset.add(uusi_varaus);
            tietokanta.luoVaraus(uusi_varaus);

            varaustenlistaLW.getItems().add(haeVaraukseenSyotetytTiedot());
            paivitatilastot();
        } catch (Exception e) {
            System.out.println("Varauksen luonti epäonnistui.");
        }
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
            System.out.println("Tiedoja ei täytetty oikein.");
        }
    }

    private void taytamokkikentat(Mokki mokki) {
        try {
            mokinnimiTF.setText(mokki.getNimi());
            vrkhintaTF.setText(Integer.toString(mokki.getHintaVrk()));
            sijaintiTF.setText(mokki.getSijainti());
            hlomaaraTF2.setText(Integer.toString(mokki.getHenkilomaara()));
            mukavuudetTA.setText(mokki.getMukavuudet());
            neliomaaraTF.setText(Integer.toString(mokki.getKokoM2()));
            kuvausTA.setText(mokki.getKuvaus());
        } catch (Exception e) {
            System.out.println("Tietoja ei täytetty oikein.");
        }
    }

    @FXML
    private void muokkauksentallennus() {
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
            varauslista = -1;
        }
    }

    @FXML
    private int varauslista;

    @FXML
    private void poistavaraus() {
        int varauslista = varaustenlistaLW.getSelectionModel().getSelectedIndex();
        if (varauslista >= 0) {
            varaustenlistaLW.getItems().remove(varauslista);
        } else {
            System.out.println("Varausta ei poistettu. Tuplaklikkaa poistettavaa varausta ja kokeile uudelleen.");
        }
    }

    public String haeVaraukseenSyotetytTiedot() {
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
            System.out.println("Laskua ei luotu. Kokeile uudelleen.");
        }
    }

    @FXML
    private void poistalasku() {
        int varauslista = laskujenlistaLW.getSelectionModel().getSelectedIndex();
        if (varauslista >= 0) {
            laskujenlistaLW.getItems().remove(varauslista);
        } else {
            System.out.println("Laskua ei poistettu. Tuplaklikkaa poistettavaa laskua ja kokeile uudelleen.");
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
            System.out.println("Laskun kenttien täytössä virhe! Tarkista syöte.");
        }
    }

    @FXML
    private void laskujentuplaklikkaus() {
        laskujenlistaLW.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                muokattavienlaskujemaara = laskujenlistaLW.getSelectionModel().getSelectedIndex();
                String valittu = laskujenlistaLW.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    taytaLaskuKentat(valittu);
                    laskujenlistaLW.refresh();
                }
            }
        });
        listanmuokkaus();
    }

    public String tilastolista() {
        int varaustenMaara = varaustenlistaLW.getItems().size();

        double kokonaisOleskelu = 0;
        double kokonaistuotto = 0;
        Map<String, Integer> varausMaaraPerMokki = new HashMap<>();
        Map<String, Double> tuottoPerMokki = new HashMap<>();

        for (String lasku : laskujenlistaLW.getItems()) {
            String[] rivit = lasku.split("\n");
            String mokki = "";
            double hinta = 0;
            int vrkMaara = 0;

            for (String rivi : rivit) {
                if (rivi.startsWith("Mökin nimi:")) {
                    mokki = rivi.split(":")[1].trim();
                } else if (rivi.startsWith("Hinta (vrk):")) {
                    String poistetaaneuro = rivi.split(":")[1].replace("€", "").trim();
                    hinta = Double.parseDouble(poistetaaneuro);
                } else if (rivi.startsWith("Aika (vrk):")) {
                    vrkMaara = Integer.parseInt(rivi.split(":")[1].trim());
                }
            }

            kokonaisOleskelu += vrkMaara;
            double tuotto = hinta * vrkMaara;
            kokonaistuotto += tuotto;

            varausMaaraPerMokki.put(mokki, varausMaaraPerMokki.getOrDefault(mokki, 0) + 1);
            tuottoPerMokki.put(mokki, tuottoPerMokki.getOrDefault(mokki, 0.0) + tuotto);
        }

        double keskimOleskelu = varaustenMaara > 0 ? kokonaisOleskelu / varaustenMaara : 0;
        double keskimTuotto = varaustenMaara > 0 ? kokonaistuotto / varaustenMaara : 0;

        StringBuilder raportti = new StringBuilder();
        raportti.append("Kaikkien varausten määrä: ").append(varaustenMaara).append("\n");
        raportti.append("Keskimääräinen oleskelun kesto: ").append(String.format("%.1f", keskimOleskelu)).append(" päivää\n");

        raportti.append("Varausmäärät per mökki:\n");
        for (String mokki : varausMaaraPerMokki.keySet()) {
            raportti.append(" - ").append(mokki).append(": ").append(varausMaaraPerMokki.get(mokki)).append(" varausta\n");
        }

        raportti.append("Kokonaistuotto: ").append(String.format("%.2f", kokonaistuotto)).append(" €\n");

        raportti.append("Tuotto per mökki:\n");
        for (String mokki : tuottoPerMokki.keySet()) {
            raportti.append(" - ").append(mokki).append(": ").append(String.format("%.2f", tuottoPerMokki.get(mokki))).append(" €\n");
        }

        raportti.append("Keskimääräinen tuotto per varaus: ").append(String.format("%.2f", keskimTuotto)).append(" €\n");

        return raportti.toString();
    }

    private void paivitatilastot(){
        raportointilistaLW.getItems().clear();
        String[] rivit = tilastolista().split("\n");
        raportointilistaLW.getItems().addAll(Arrays.asList(rivit));
    }

}
