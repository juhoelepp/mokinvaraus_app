package ot1.mokinvaraus_app;

import java.sql.*;
import java.util.ArrayList;

public class Tietokanta {
    private final String url = "jdbc:mysql://localhost:3306/mokinvaraus";
    private final String user = "root";
    private final String pass = "1234";

    public ArrayList<Mokki> haeMokit() {
        ArrayList<Mokki> mokit = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement s = connection.createStatement();
            ResultSet tulos = s.executeQuery("SELECT * FROM MOKIT");
            while (tulos.next()) {
                Mokki haettu_mokki = new Mokki();

                haettu_mokki.setMokkiID(tulos.getInt(1));
                haettu_mokki.setNimi(tulos.getString(2));
                haettu_mokki.setHintaVrk(tulos.getInt(3));
                haettu_mokki.setSijainti(tulos.getString(4));
                haettu_mokki.setHenkilomaara(tulos.getInt(5));
                haettu_mokki.setMukavuudet(tulos.getString(6));
                haettu_mokki.setKokoM2(tulos.getInt(7));
                haettu_mokki.setKuvaus(tulos.getString(8));

                mokit.add(haettu_mokki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mokit;
    }

    public ArrayList<Asiakas> haeAsiakkaat() {
        ArrayList<Asiakas> asiakkaat = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement s = connection.createStatement();
            ResultSet tulos = s.executeQuery("SELECT * FROM ASIAKKAAT");
            while (tulos.next()) {
                Asiakas haettu_asiakas = new Asiakas();

                haettu_asiakas.setAsiakasID(tulos.getInt(1));
                haettu_asiakas.setNimi(tulos.getString(2));
                haettu_asiakas.setPuhelinnumero(tulos.getString(3));
                haettu_asiakas.setSahkoposti(tulos.getString(4));
                haettu_asiakas.setOsoite(tulos.getString(5));
                haettu_asiakas.setYritys(tulos.getString(6));

                asiakkaat.add(haettu_asiakas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asiakkaat;
    }

    public ArrayList<Varaus> haeVaraukset() {
        ArrayList<Varaus> varaukset = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement s = connection.createStatement();
            ResultSet tulos = s.executeQuery("SELECT * FROM VARAUKSET");
            while (tulos.next()) {
                Varaus haettu_varaus = new Varaus();

                haettu_varaus.setVarausID(tulos.getInt(1));
                haettu_varaus.setKestoPaivia(tulos.getInt(2));
                haettu_varaus.setAsiakasID(tulos.getInt(3));
                haettu_varaus.setMokkiID(tulos.getInt(4));
                haettu_varaus.setHenkilomaara(tulos.getInt(5));
                haettu_varaus.setToiveet(tulos.getString(6));
                haettu_varaus.setAsiakaspalvelijaID(tulos.getInt(7));
                haettu_varaus.setAloitusPvm(tulos.getDate(8));
                haettu_varaus.setLopetusPvm(tulos.getDate(9));
                haettu_varaus.setVarauksenTila(tulos.getString(10));

                varaukset.add(haettu_varaus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return varaukset;
    }

    public ArrayList<Lasku> haeLaskut() {
        ArrayList<Lasku> laskut = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement s = connection.createStatement();
            ResultSet tulos = s.executeQuery("SELECT * FROM LASKUT");
            while (tulos.next()) {
                Lasku haettu_lasku = new Lasku();

                haettu_lasku.setLaskuID(tulos.getInt(1));
                haettu_lasku.setAsiakasID(tulos.getInt(2));
                haettu_lasku.setMokkiID(tulos.getInt(3));
                haettu_lasku.setKokonaishinta(tulos.getInt(4));
                haettu_lasku.setVarausID(tulos.getInt(5));

                laskut.add(haettu_lasku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return laskut;
    }
    
    public void paivitaMokki(Mokki mokki) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "UPDATE MOKIT SET nimi=?, hinta_vrk=?, sijainti=?, henkilomaara=?, mukavuudet=?, koko_m2=?, kuvaus=? WHERE mokki_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, mokki.getNimi());
                ps.setDouble(2, mokki.getHintaVrk());
                ps.setString(3, mokki.getSijainti());
                ps.setInt(4, mokki.getHenkilomaara());
                ps.setString(5, mokki.getMukavuudet());
                ps.setDouble(6, mokki.getKokoM2());
                ps.setString(7, mokki.getKuvaus());
                ps.setInt(8, mokki.getMokkiID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Updated " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaAsiakas(Asiakas asiakas) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "DELETE FROM ASIAKKAAT  WHERE asiakas_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, asiakas.getAsiakasID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Deleted " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lisaaAsiakas(Asiakas asiakas) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO ASIAKKAAT (asiakas_id, nimi, puhelinnumero, sahkoposti, osoite, yritys) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, asiakas.getAsiakasID());
                ps.setString(2, asiakas.getNimi());
                ps.setString(3, asiakas.getPuhelinnumero());
                ps.setString(4, asiakas.getSahkoposti());
                ps.setString(5, asiakas.getOsoite());
                ps.setString(6, asiakas.getYritys());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaVaraus(Varaus varaus) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "DELETE FROM VARAUKSET  WHERE varaus_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, varaus.getVarausID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Deleted " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lisaaVaraus(Varaus varaus) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO VARAUKSET (varaus_id, kesto_paivia, asiakas_id, mokki_id, henkilomaara, toiveet, asiakaspalvelija_id, aloitus_pvm, lopetus_pvm, varauksen_tila) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, varaus.getVarausID());
                ps.setInt(2, varaus.getKestoPaivia());
                ps.setInt(3, varaus.getAsiakasID());
                ps.setInt(4, varaus.getMokkiID());
                ps.setInt(5, varaus.getHenkilomaara());
                ps.setString(6, varaus.getToiveet());
                ps.setInt(7, varaus.getAsiakaspalvelijaID());
                ps.setDate(8, varaus.getAloitusPvm());
                ps.setDate(9, varaus.getLopetusPvm());
                ps.setString(10, varaus.getVarauksenTila());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void paivitaVaraus(Varaus varaus) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "UPDATE VARAUKSET SET kesto_paivia=?, asiakas_id=?, mokki_id=?, henkilomaara=?, toiveet=?, asiakaspalvelija_id=?, aloitus_pvm=?, lopetus_pvm=?, varauksen_tila=? WHERE varaus_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, varaus.getKestoPaivia());
                ps.setInt(2, varaus.getAsiakasID());
                ps.setInt(3, varaus.getMokkiID());
                ps.setInt(4, varaus.getHenkilomaara());
                ps.setString(5, varaus.getToiveet());
                ps.setInt(6, varaus.getAsiakaspalvelijaID());
                ps.setDate(7, varaus.getAloitusPvm());
                ps.setDate(8, varaus.getLopetusPvm());
                ps.setString(9, varaus.getVarauksenTila());
                ps.setInt(10, varaus.getVarausID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Updated " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaLasku(Lasku lasku) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "DELETE FROM LASKUT WHERE lasku_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, lasku.getVarausID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Deleted " + rowsAffected + " row(s).");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void lisaaLasku(Lasku lasku) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO LASKUT (lasku_id, asiakas_id, mokki_id, kokonaishinta, varaus_id) values (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, lasku.getLaskuID());
                ps.setInt(2, lasku.getAsiakasID());
                ps.setInt(3, lasku.getMokkiID());
                ps.setInt(4, lasku.getKokonaishinta());
                ps.setInt(5, lasku.getVarausID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void paivitaLasku(Lasku lasku) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "UPDATE LASKUT SET asiakas_id=?, mokki_id=?, kokonaishinta=?, varaus_id=? WHERE lasku_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, lasku.getAsiakasID());
                ps.setInt(2, lasku.getMokkiID());
                ps.setInt(3, lasku.getKokonaishinta());
                ps.setInt(4, lasku.getVarausID());
                ps.setInt(5, lasku.getLaskuID());

                int rowsAffected = ps.executeUpdate();
                System.out.println("Updated " + rowsAffected + " row(s).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
