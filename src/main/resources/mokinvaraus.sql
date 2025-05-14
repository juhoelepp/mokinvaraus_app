DROP TABLE VARAUKSET;
DROP TABLE MOKIT;
DROP TABLE ASIAKKAAT;
DROP TABLE ASIAKASPALVELIJAT;

CREATE TABLE IF NOT EXISTS MOKIT(
    mokki_id int PRIMARY KEY,
    nimi varchar(255) not null,
    hinta_vrk int,
    sijainti varchar(255) not null,
    henkilomaara int,
    mukavuudet varchar(2048) not null,
    koko_m2 int,
    kuvaus varchar(2048) not null
);

CREATE TABLE IF NOT EXISTS ASIAKKAAT(
    asiakas_id int PRIMARY KEY,
    nimi varchar(255) not null,
    puhelinnumero varchar(255) not null,
    sahkoposti varchar(255) not null,
    osoite varchar(255) not null,
    yritys varchar(255) not null
);

CREATE TABLE IF NOT EXISTS ASIAKASPALVELIJAT(
    asiakaspalvelija_id int PRIMARY KEY,
    nimi varchar(255) not null,
    sahkoposti varchar(255) not null,
    puhelinnumero varchar(255) not null
);

CREATE TABLE IF NOT EXISTS VARAUKSET(
    varaus_id int PRIMARY KEY,
    kesto_paivia int,
    asiakas_id int,
    FOREIGN KEY (asiakas_id) REFERENCES ASIAKKAAT(asiakas_id),
    mokki_id int,
    FOREIGN KEY (mokki_id) REFERENCES MOKIT(mokki_id),
    henkilomaara int,
    toiveet varchar(255) not null,
    asiakaspalvelija_id int,
    FOREIGN KEY (asiakaspalvelija_id) REFERENCES ASIAKASPALVELIJAT(asiakaspalvelija_id),
    aloitus_pvm Date,
    lopetus_pvm Date,
    varauksen_tila varchar(255) not null
);

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (1, 'Metsämaa', 80, 'Mökkikodit-lomakylä', 4, 'Televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin, kahvinkeitin, kuivauskaappi, sauna.',
        56, 'Kotoisa perinteikäs mökki luonnon helmassa.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (2, 'Metsäkoto', 80, 'Mökkikodit-lomakylä', 4, 'Televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin, kahvinkeitin, kuivauskaappi, sauna.',
        56, 'Kotoisa perinteikäs mökki luonnon helmassa.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (3, 'Metsän vartija', 100, 'Mökkikodit-lomakylä', 5, 'Ilmainen Wi-Fi, ilmastointi, televisio, mikroaaltouuni, liesi, uuni, jääkaappi,
vedenkeitin, kahvinkeitin, kuivauskaappi, sauna.', 74, 'Siisti mökki perustarpeilla kallion päällä.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (4, 'Mesipolku', 115, 'Mökkikodit-lomakylä', 5, 'Ilmainen Wi-Fi, ilmastointi, televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin,
kahvinkeitin, kuivauskaappi, sauna', 84, 'Upea mökki kauniissa luonnonmaisemissa.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (5, 'Mörrimöykky', 130, 'Raatteentie 260', 10, 'Ulko-WC, ilmastointi, televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin, sauna',
        150, 'Vanha jätkänkämppä keskellä erämaata.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (6, 'Myrskyn maja', 150, 'Mökkikodit-lomakylä', 6, 'Kuin hevosaitauksessa konsanaan olisi. Niin no... toki kuin kuningasori.', 110, 'Ykkösen, kakkosen elikkäs hevosmies-Makkosen lemppari mökki. Makkonen suosittelee!');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (7, 'Mäntymaja', 240, 'Aatelistonmäki 3', 8, '5G-nettiyhteys, ilmastointi, 70-tuumainen 4K-televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin,
kahvinkeitin, kuivauskaappi, ulkoporeallas, palju, viihdejärjestelmä, isot ikkunat, moderni keittiö, kylpyamme, kaksi suihkua, puusauna.', 140,
        'Hulppea, yleellinen ja vasta valmistunut mökki hienoilla herkuilla ja hyvillä yhteyksillä.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (8, 'Mustikkarinne', 350, 'Porvarienpolku 1', 12, '5G ja valokuitunetti, ilmastointi, 8K-televisio, mikroaaltouuni, liesi, uuni, jääkaappi, vedenkeitin,
kahvinkeitin, kuivauskaappi, sisä- ja ulkoporeallas, palju, älykäs viihdejärjestelmä, isot ikkunat, moderni keittiö, viinikaappi, kylpyamme, neljä suihkua, luxus-sauna.', 240,
        'Luksusta pursuava ja katseita keräävä mökki kaikilla mausteilla ja aivan upealla järvimaisemalla.');

INSERT INTO ASIAKASPALVELIJAT (asiakaspalvelija_id, nimi, sahkoposti, puhelinnumero)
VALUES (1, 'Tomppa', 'tomppa@mokkikodit.fi', '0440339999');