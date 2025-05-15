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
    ArrayList<Lasku> laskut;

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
    private TextField hlomaaraTF3;

    @FXML
    private TextArea toiveetTF;

    @FXML
    private TextArea toiveetTF2;

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
        laskut = tietokanta.haeLaskut();

        for (Mokki mokki : mokit) {
            mokkiCB.getItems().add(mokki.getNimi());
            mokki2CB.getItems().add(mokki.getNimi());

            mokkilistaLW.getItems().add(mokki.getNimi());
        }

        for (Varaus varaus : varaukset) {
            varaustenlistaLW.getItems().add(Integer.toString(varaus.getVarausID()));
        }

        for (Lasku lasku : laskut) {
            laskujenlistaLW.getItems().add(Integer.toString(lasku.getLaskuID()));
        }

        paivitatilastot();
    }

    @FXML
    private void varaustentuplaklikkaus(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String valittu = varaustenlistaLW.getSelectionModel().getSelectedItem();
            if (valittu != null) {
                for (Varaus varaus : varaukset) {
                    if (varaus.getVarausID() == Integer.parseInt(valittu)) {
                        taytavarauskentat(varaus);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    private void mokkientuplaklikkaus(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String valittu = mokkilistaLW.getSelectionModel().getSelectedItem();
            if (valittu != null) {
                for (Mokki mokki : mokit) {
                    if (mokki.getNimi().equals(valittu)) {
                        taytamokkikentat(mokki);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    private void mokinMuokkauksenTallennus() {
        int ivalittu_mokki = mokkilistaLW.getSelectionModel().getSelectedIndex();

        if (ivalittu_mokki < 0) { return; }
        String valittu_mokki = mokkilistaLW.getSelectionModel().getSelectedItem();

        Mokki haluttu_mokki = null;
        for (Mokki mokki : mokit) {
            if (mokki.getNimi().equals(valittu_mokki)) {
                haluttu_mokki = mokki;
                break;
            }
        }

        if (haluttu_mokki != null) {
            try {
                Mokki muokattu_mokki = new Mokki();
                muokattu_mokki.setMokkiID(haluttu_mokki.getMokkiID());
                muokattu_mokki.setNimi(mokinnimiTF.getText());
                muokattu_mokki.setHintaVrk(Integer.parseInt(vrkhintaTF.getText()));
                muokattu_mokki.setSijainti(sijaintiTF.getText());
                muokattu_mokki.setHenkilomaara(Integer.parseInt(hlomaaraTF3.getText()));
                muokattu_mokki.setMukavuudet(mukavuudetTA.getText());
                muokattu_mokki.setKuvaus(kuvausTA.getText());

                mokit.remove(haluttu_mokki);
                mokit.add(muokattu_mokki);

                tietokanta.paivitaMokki(muokattu_mokki);

                mokinnimiTF.clear();
                vrkhintaTF.clear();
                sijaintiTF.clear();
                hlomaaraTF3.clear();
                mukavuudetTA.clear();
                kuvausTA.clear();
            } catch (Exception e) {
                System.out.println("Mökin muokkaus epäonnistui.");
            }
        }
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
            String varattava_mokki = mokkiCB.getValue();
            boolean mokki_asetettu = false;
            for (Mokki mokki : mokit) {
                if (mokki.getNimi().equals(varattava_mokki)) {
                    uusi_varaus.setMokkiID(mokki.getMokkiID());
                    mokki_asetettu = true;
                    break;
                }
            }
            if (!mokki_asetettu) {
                throw new Exception();
            }
            uusi_varaus.setHenkilomaara(Integer.parseInt(hlomaaraTF.getText()));
            uusi_varaus.setToiveet(toiveetTF.getText());
            uusi_varaus.setAsiakaspalvelijaID(1);
            uusi_varaus.setAloitusPvm(Date.valueOf(alkuDP.getValue()));
            uusi_varaus.setLopetusPvm(Date.valueOf(lopetusDP.getValue()));
            uusi_varaus.setVarauksenTila("Varattu.");
            uusi_varaus.setAsiakasID(luoAsiakas());

            varaukset.add(uusi_varaus);
            tietokanta.lisaaVaraus(uusi_varaus);

            varaustenlistaLW.getItems().add(Integer.toString(uusi_varaus.getVarausID()));
            luolasku(uusi_varaus);
            paivitatilastot();

            alkuDP.setValue(null);
            lopetusDP.setValue(null);
            mokkiCB.getSelectionModel().clearSelection();
            hlomaaraTF.clear();
            toiveetTF.clear();
            etunimiTF.clear();
            sukunimiTF.clear();
            puhnroTF.clear();
            spostiTF.clear();
            osoiteTF.clear();
            yritysTF.clear();
        } catch (Exception e) {
            System.out.println("Varauksen luonti epäonnistui.");
        }
    }

    private void taytavarauskentat(Varaus varaus) {
        try {
            for (Asiakas asiakas : asiakkaat) {
                if (varaus.getAsiakasID() == asiakas.getAsiakasID()) {
                    etunimi2TF.setText(asiakas.getNimi().split(" ")[0].trim());
                    sukunimi2TF.setText(asiakas.getNimi().split(" ")[1].trim());
                    puhnro2TF.setText(asiakas.getPuhelinnumero());
                    sposti2TF.setText(asiakas.getSahkoposti());
                    osoite2TF.setText(asiakas.getOsoite());
                    yritys2TF.setText(asiakas.getYritys());
                    break;
                }
            }

            hlomaaraTF2.setText(Integer.toString(varaus.getHenkilomaara()));
            toiveetTF2.setText(varaus.getToiveet());
            alku2DP.setValue(varaus.getAloitusPvm().toLocalDate());
            lopetus2DP.setValue(varaus.getLopetusPvm().toLocalDate());

            for (Mokki mokki : mokit) {
                if (mokki.getMokkiID() == varaus.getMokkiID()) {
                    mokki2CB.setValue(mokki.getNimi());
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Tietoja ei täytetty oikein.");
        }
    }

    private void taytamokkikentat(Mokki mokki) {
        try {
            mokinnimiTF.setText(mokki.getNimi());
            vrkhintaTF.setText(Integer.toString(mokki.getHintaVrk()));
            sijaintiTF.setText(mokki.getSijainti());
            hlomaaraTF3.setText(Integer.toString(mokki.getHenkilomaara()));
            mukavuudetTA.setText(mokki.getMukavuudet());
            neliomaaraTF.setText(Integer.toString(mokki.getKokoM2()));
            kuvausTA.setText(mokki.getKuvaus());
        } catch (Exception e) {
            System.out.println("Tietoja ei täytetty oikein.");
        }
    }

    private void tyhjennaVarauksenMuokkausKentat() {
        etunimi2TF.clear();
        sukunimi2TF.clear();
        puhnro2TF.clear();
        sposti2TF.clear();
        osoite2TF.clear();
        yritys2TF.clear();
        hlomaaraTF2.clear();
        toiveetTF2.clear();
        alku2DP.setValue(null);
        lopetus2DP.setValue(null);
        mokki2CB.getSelectionModel().clearSelection();
    }


    @FXML
    private void varauksenMuokkauksenTallennus() {
        int ivalittu_varaus = varaustenlistaLW.getSelectionModel().getSelectedIndex();

        if (ivalittu_varaus < 0) { return; }
        int valittu_varaus = Integer.parseInt(varaustenlistaLW.getSelectionModel().getSelectedItem());

        Varaus haluttu_varaus = null;
        for (Varaus varaus : varaukset) {
            if (varaus.getVarausID() == valittu_varaus) {
                haluttu_varaus = varaus;
                break;
            }
        }

        if (haluttu_varaus != null) {
            try {
                Varaus muokattu_varaus = new Varaus();
                muokattu_varaus.setVarausID(haluttu_varaus.getVarausID());
                LocalDate aloitus_pvm = alku2DP.getValue();
                LocalDate lopetus_pvm = lopetus2DP.getValue();
                muokattu_varaus.setKestoPaivia((int) ChronoUnit.DAYS.between(aloitus_pvm, lopetus_pvm));
                muokattu_varaus.setAsiakasID(haluttu_varaus.getAsiakasID());

                for (Mokki mokki : mokit) {
                    if (mokki.getNimi().equals(mokki2CB.getValue())) {
                        muokattu_varaus.setMokkiID(mokki.getMokkiID());
                        break;
                    }
                }

                muokattu_varaus.setHenkilomaara(Integer.parseInt(hlomaaraTF2.getText()));
                muokattu_varaus.setToiveet(toiveetTF2.getText());
                muokattu_varaus.setAsiakaspalvelijaID(haluttu_varaus.getAsiakaspalvelijaID());
                muokattu_varaus.setAloitusPvm(Date.valueOf(aloitus_pvm));
                muokattu_varaus.setLopetusPvm(Date.valueOf(lopetus_pvm));
                muokattu_varaus.setVarauksenTila(haluttu_varaus.getVarauksenTila());

                varaukset.remove(haluttu_varaus);
                varaukset.add(muokattu_varaus);

                tietokanta.paivitaVaraus(muokattu_varaus);

                tyhjennaVarauksenMuokkausKentat();
            } catch (Exception e) {
                System.out.println("Varauksen muokkaus epäonnistui.");
            }
        }
    }

    @FXML
    private void poistavaraus() {
        int ivalittu_varaus = varaustenlistaLW.getSelectionModel().getSelectedIndex();

        if (ivalittu_varaus < 0) { return; }
        int valittu_varaus = Integer.parseInt(varaustenlistaLW.getSelectionModel().getSelectedItem());

        Varaus haluttu_varaus = null;
        for (Varaus varaus : varaukset) {
            if (varaus.getVarausID() == valittu_varaus) {
                haluttu_varaus = varaus;
                break;
            }
        }

        if (haluttu_varaus != null) {
            varaukset.remove(haluttu_varaus);
            tietokanta.poistaVaraus(haluttu_varaus);
            varaustenlistaLW.getItems().remove(ivalittu_varaus);
            varaustenlistaLW.refresh();

            Asiakas haluttu_asiakas = null;
            for (Asiakas asiakas : asiakkaat) {
                if (asiakas.getAsiakasID() == haluttu_varaus.getAsiakasID()) {
                    haluttu_asiakas = asiakas;
                    break;
                }
            }

            if (haluttu_asiakas != null) {
                asiakkaat.remove(haluttu_asiakas);
                tietokanta.poistaAsiakas(haluttu_asiakas);
            }

            for (Lasku lasku : laskut) {
                if (lasku.getVarausID() == haluttu_varaus.getVarausID()) {
                    laskujenlistaLW.getItems().remove(Integer.toString(lasku.getLaskuID()));
                }
            }

            tyhjennaVarauksenMuokkausKentat();
            tyhjennaLaskunMuokkausKentat();
            paivitatilastot();
        }
    }

    private void tyhjennaLaskunMuokkausKentat() {
        etunimi3TF.clear();
        sukunimi3TF.clear();
        sposti3TF.clear();
        mokinnimi2TF.clear();
        vrkhinta2TF.clear();
        vrkaikaTF.clear();
        kokhintaTF.clear();
    }

    @FXML
    private void luolasku(Varaus varaus) {
        try {
            int vrkhinta = 0;
            long vrkaika = 0;

            if (vrkhintaTF.getText() != null && !vrkhintaTF.getText().isEmpty()) {
                vrkhinta = Integer.parseInt(vrkhintaTF.getText());
            }

            if (alkuDP.getValue() != null && lopetusDP.getValue() != null) {
                vrkaika = ChronoUnit.DAYS.between(alkuDP.getValue(), lopetusDP.getValue());
                if (vrkaika == 0) vrkaika = 1;
            }

            int kokonaishinta = Math.toIntExact(vrkhinta * vrkaika);

            Lasku uusi_lasku = new Lasku();
            uusi_lasku.setLaskuID(laskut.size() + 1);
            uusi_lasku.setAsiakasID(varaus.getAsiakasID());
            uusi_lasku.setMokkiID(varaus.getMokkiID());
            uusi_lasku.setKokonaishinta(kokonaishinta);
            uusi_lasku.setVarausID(varaus.getVarausID());

            laskut.add(uusi_lasku);
            tietokanta.lisaaLasku(uusi_lasku);
            laskujenlistaLW.getItems().add(Integer.toString(uusi_lasku.getLaskuID()));

        } catch (Exception e) {
            System.out.println("Laskua ei luotu. Kokeile uudelleen.");
        }
    }

    @FXML
    private void poistalasku() {
        int ivalittu_lasku = laskujenlistaLW.getSelectionModel().getSelectedIndex();

        if (ivalittu_lasku < 0) { return; }
        int valittu_lasku = Integer.parseInt(laskujenlistaLW.getSelectionModel().getSelectedItem());

        Lasku haluttu_lasku = null;
        for (Lasku lasku : laskut) {
            if (lasku.getLaskuID() == valittu_lasku) {
                haluttu_lasku = lasku;
                break;
            }
        }

        if (haluttu_lasku != null) {
            laskut.remove(haluttu_lasku);
            tietokanta.poistaLasku(haluttu_lasku);
            laskujenlistaLW.getItems().remove(ivalittu_lasku);
            laskujenlistaLW.refresh();

            tyhjennaLaskunMuokkausKentat();
            paivitatilastot();
        }
    }

    @FXML
    private void tallennalasku() {
        int ivalittu_lasku = laskujenlistaLW.getSelectionModel().getSelectedIndex();

        if (ivalittu_lasku < 0) { return; }
        int valittu_lasku = Integer.parseInt(laskujenlistaLW.getSelectionModel().getSelectedItem());

        Lasku haluttu_lasku = null;
        for (Lasku lasku : laskut) {
            if (lasku.getLaskuID() == valittu_lasku) {
                haluttu_lasku = lasku;
                break;
            }
        }

        if (haluttu_lasku != null) {
            try {
                Lasku muokattu_lasku = new Lasku();
                muokattu_lasku.setLaskuID(haluttu_lasku.getLaskuID());
                muokattu_lasku.setAsiakasID(haluttu_lasku.getAsiakasID());
                muokattu_lasku.setMokkiID(haluttu_lasku.getMokkiID());
                muokattu_lasku.setKokonaishinta(Integer.parseInt(kokhintaTF.getText()));
                muokattu_lasku.setVarausID(haluttu_lasku.getVarausID());

                laskut.remove(haluttu_lasku);
                laskut.add(muokattu_lasku);
                tietokanta.paivitaLasku(muokattu_lasku);

                tyhjennaLaskunMuokkausKentat();
            } catch (Exception e) {
                System.out.println("Laskun muokkaus epäonnistui.");
            }
        }
    }

    @FXML
    private void taytaLaskuKentat(Lasku lasku) {
        try {
            for (Asiakas asiakas : asiakkaat) {
                if (asiakas.getAsiakasID() == lasku.getAsiakasID()) {
                    etunimi3TF.setText(asiakas.getNimi().split(" ")[0].trim());
                    sukunimi3TF.setText(asiakas.getNimi().split(" ")[1].trim());
                    sposti3TF.setText(asiakas.getSahkoposti());
                    break;
                }
            }

            for (Mokki mokki : mokit) {
                if (mokki.getMokkiID() == lasku.getMokkiID()) {
                    mokinnimi2TF.setText(mokki.getNimi());
                    vrkhinta2TF.setText(Integer.toString(mokki.getHintaVrk()));
                    break;
                }
            }

            for (Varaus varaus : varaukset) {
                if (varaus.getVarausID() == lasku.getVarausID()) {
                    vrkaikaTF.setText(Integer.toString(varaus.getKestoPaivia()));
                    kokhintaTF.setText(Integer.toString(lasku.getKokonaishinta()));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Laskun kenttien täytössä virhe! Tarkista syöte.");
        }
    }

    @FXML
    private void laskujentuplaklikkaus() {
        laskujenlistaLW.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String valittu = laskujenlistaLW.getSelectionModel().getSelectedItem();
                if (valittu != null) {
                    for (Lasku lasku : laskut) {
                        if (lasku.getLaskuID() == Integer.parseInt(valittu)) {
                            taytaLaskuKentat(lasku);
                            break;
                        }
                    }
                    laskujenlistaLW.refresh();
                }
            }
        });
    }

    public String tilastolista() {
        int varaustenMaara = varaukset.size();

        double kokonaisOleskelu = 0;
        double kokonaistuotto = 0;
        Map<String, Integer> varausMaaraPerMokki = new HashMap<>();
        Map<String, Double> tuottoPerMokki = new HashMap<>();

        for (Lasku lasku : laskut) {
            Mokki haluttu_mokki = null;
            for (Mokki mokki : mokit) {
                if (lasku.getMokkiID() == mokki.getMokkiID()) {
                    haluttu_mokki = mokki;
                }
            }

            Varaus haluttu_varaus = null;
            for (Varaus varaus : varaukset) {
                if (lasku.getVarausID() == varaus.getVarausID()) {
                    haluttu_varaus = varaus;
                }
            }

            if (haluttu_mokki == null || haluttu_varaus == null) { continue; }

            double hinta = haluttu_mokki.getHintaVrk();
            int vrkMaara = haluttu_varaus.getKestoPaivia();

            kokonaisOleskelu += vrkMaara;
            double tuotto = hinta * vrkMaara;
            kokonaistuotto += tuotto;

            varausMaaraPerMokki.put(haluttu_mokki.getNimi(), varausMaaraPerMokki.getOrDefault(haluttu_mokki.getNimi(), 0) + 1);
            tuottoPerMokki.put(haluttu_mokki.getNimi(), tuottoPerMokki.getOrDefault(haluttu_mokki.getNimi(), 0.0) + tuotto);
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
