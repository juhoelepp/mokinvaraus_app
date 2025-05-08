package ohjelmistotuotanto1.ot1varaus;

import java.io.Serializable;

public class Asiakastiedot implements Serializable {

    /**
     * Asiakkaan tiedot merkkijonona, lukuna ja desimaalina.
      */
    private String etunimi;

    private String sukunimi;

    private int puhelinnumero;

    private String sahkoposti;

    private String osoite;

    private String yritys;

    public Asiakastiedot(String etunimi, String sukunimi, int puhelinnumero, String sahkoposti, String osoite, String yritys) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
        this.osoite = osoite;
        this.yritys = yritys;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public int getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(int puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getYritys() {
        return yritys;
    }

    public void setYritys(String yritys) {
        this.yritys = yritys;
    }
}
