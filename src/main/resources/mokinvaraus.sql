CREATE TABLE IF NOT EXISTS MOKIT(
    mokki_id int PRIMARY KEY,
    nimi varchar(255) not null,
    hinta_vrk int,
    sijainti varchar(255) not null,
    henkilomaara int,
    mukavuudet varchar(255) not null,
    koko_m2 int,
    kuvaus varchar(255) not null
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
VALUES (1, 'Mökki Makkonen', 200, 'Saariselkä', 5, 'Jotain.', 104, 'Makkosen mökki.');

INSERT INTO MOKIT (mokki_id, nimi, hinta_vrk, sijainti, henkilomaara, mukavuudet, koko_m2, kuvaus)
VALUES (2, 'Mökki Kakkonen', 200, 'Kalajoki', 3, 'Jotain.', 74, 'Kakkosen mökki.');