package ot1.mokinvaraus_app;

import java.sql.*;
import java.util.ArrayList;

public class Tietokanta {
    public ArrayList<Mokki> haeMokit() {
        ArrayList<Mokki> mokit = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokinvaraus", "root", "1234")) {
            System.out.println(connection.isValid(0));
            Statement s = connection.createStatement();
            ResultSet ss = s.executeQuery("select * from MOKIT");
            while (ss.next()) {
                Mokki haettu_mokki = new Mokki();

                haettu_mokki.setMokkiID(ss.getInt(1));
                haettu_mokki.setNimi(ss.getString(2));
                haettu_mokki.setHintaVrk(ss.getInt(3));
                haettu_mokki.setSijainti(ss.getString(4));
                haettu_mokki.setHenkilomaara(ss.getInt(5));
                haettu_mokki.setMukavuudet(ss.getString(6));
                haettu_mokki.setKokoM2(ss.getInt(7));
                haettu_mokki.setKuvaus(ss.getString(8));

                mokit.add(haettu_mokki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mokit;
    }

    public static void main(String[] args) {
        Tietokanta mt = new Tietokanta();
        ArrayList<Mokki> mokit = mt.haeMokit();
        for (Mokki mokki : mokit) {
            System.out.println(mokki.toString());
        }
    }
}
