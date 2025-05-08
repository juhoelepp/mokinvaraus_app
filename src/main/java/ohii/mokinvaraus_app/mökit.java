package ohjelmistotuotanto1.ot1varaus;

import java.io.Serializable;

public class Mokkienhallinta implements Serializable {

    private String mokinnimi;

    private Double vrkhinta;

    private String sijainti;

    private int henkilomaara;

    private Double neliomaaram2;

    private String mukavuudet;

    private String kuvaus;

    public Mokkienhallinta(String mokinnimi, Double vrkhinta, String sijainti, int henkilomaara, Double neliomaaram2, String mukavuudet, String kuvaus) {
        this.mokinnimi = mokinnimi;
        this.vrkhinta = vrkhinta;
        this.sijainti = sijainti;
        this.henkilomaara = henkilomaara;
        this.neliomaaram2 = neliomaaram2;
        this.mukavuudet = mukavuudet;
        this.kuvaus = kuvaus;
    }

    public String getMokinnimi() {
        return mokinnimi;
    }

    public void setMokinnimi(String mokinnimi) {
        this.mokinnimi = mokinnimi;
    }

    public Double getVrkhinta() {
        return vrkhinta;
    }

    public void setVrkhinta(Double vrkhinta) {
        this.vrkhinta = vrkhinta;
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

    public Double getNeliomaaram2() {
        return neliomaaram2;
    }

    public void setNeliomaaram2(Double neliomaaram2) {
        this.neliomaaram2 = neliomaaram2;
    }

    public String getMukavuudet() {
        return mukavuudet;
    }

    public void setMukavuudet(String mukavuudet) {
        this.mukavuudet = mukavuudet;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Override
    public String toString() {
        return "Mökin nimi: " + mokinnimi + '\n' +
                "Hinta (vrk): " + vrkhinta + '\n' +
                "Sijainti: " + sijainti + '\n' +
                "Henkilömäärä: " + henkilomaara + '\n' +
                "Koko (m^2): " + neliomaaram2 + '\n' +
                "Mukavuudet: " + mukavuudet + '\n' +
                "Kuvaus: " + kuvaus + '\n';
    }
}
