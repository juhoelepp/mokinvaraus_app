package ot1.mokinvaraus_app;

public class Lasku {
    int laskuID;
    int asiakasID;
    int mokkiID;
    int kokonaishinta;
    int varausID;

    public int getLaskuID() {
        return laskuID;
    }

    public void setLaskuID(int laskuID) {
        this.laskuID = laskuID;
    }

    public int getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
    }

    public int getMokkiID() {
        return mokkiID;
    }

    public void setMokkiID(int mokkiID) {
        this.mokkiID = mokkiID;
    }

    public int getKokonaishinta() {
        return kokonaishinta;
    }

    public void setKokonaishinta(int kokonaishinta) {
        this.kokonaishinta = kokonaishinta;
    }

    public int getVarausID() {
        return varausID;
    }

    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }
}
