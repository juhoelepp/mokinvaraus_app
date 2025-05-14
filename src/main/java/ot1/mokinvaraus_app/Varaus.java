package ot1.mokinvaraus_app;

import java.sql.Date;

public class Varaus {
    int varausID;
    int kestoPaivia;
    int asiakasID;
    int mokkiID;
    int henkilomaara;
    String toiveet;
    int asiakaspalvelijaID;
    Date aloitusPvm;
    Date lopetusPvm;
    String varauksenTila;

    public int getVarausID() {
        return varausID;
    }

    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }

    public int getKestoPaivia() {
        return kestoPaivia;
    }

    public void setKestoPaivia(int kestoPaivia) {
        this.kestoPaivia = kestoPaivia;
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

    public int getHenkilomaara() {
        return henkilomaara;
    }

    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public String getToiveet() {
        return toiveet;
    }

    public void setToiveet(String toiveet) {
        this.toiveet = toiveet;
    }

    public int getAsiakaspalvelijaID() {
        return asiakaspalvelijaID;
    }

    public void setAsiakaspalvelijaID(int asiakaspalvelijaID) {
        this.asiakaspalvelijaID = asiakaspalvelijaID;
    }

    public Date getAloitusPvm() {
        return aloitusPvm;
    }

    public void setAloitusPvm(Date aloitusPvm) {
        this.aloitusPvm = aloitusPvm;
    }

    public Date getLopetusPvm() {
        return lopetusPvm;
    }

    public void setLopetusPvm(Date lopetusPvm) {
        this.lopetusPvm = lopetusPvm;
    }

    public String getVarauksenTila() {
        return varauksenTila;
    }

    public void setVarauksenTila(String varauksenTila) {
        this.varauksenTila = varauksenTila;
    }
}
