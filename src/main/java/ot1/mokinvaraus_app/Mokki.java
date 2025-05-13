package ot1.mokinvaraus_app;

public class Mokki {
    int mokkiID;
    String nimi;
    int hintaVrk;
    String sijainti;
    int henkilomaara;
    String mukavuudet;
    int kokoM2;
    String kuvaus;

    public int getMokkiID() {
        return mokkiID;
    }

    public void setMokkiID(int mokkiID) {
        this.mokkiID = mokkiID;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getHintaVrk() {
        return hintaVrk;
    }

    public void setHintaVrk(int hintaVrk) {
        this.hintaVrk = hintaVrk;
    }

    public String getSijainti() {
        return sijainti;
    }

    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    public int getHenkilomaara() {
        return henkilomaara;
    }

    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public String getMukavuudet() {
        return mukavuudet;
    }

    public void setMukavuudet(String mukavuudet) {
        this.mukavuudet = mukavuudet;
    }

    public int getKokoM2() {
        return kokoM2;
    }

    public void setKokoM2(int kokoM2) {
        this.kokoM2 = kokoM2;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %s %d %s %d %s", mokkiID, nimi, hintaVrk, sijainti, henkilomaara, mukavuudet, kokoM2, kuvaus);
    }
}
