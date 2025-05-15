# Suppea OT1-projektitehtävä - Mökkikodit
Projektitehtävän aiheena oli luoda Itä-Suomessa sijaitsevan Mökkikodit lomakylän 
henkilöstön sisäiseen käyttöön tarkoitettu varausjärjestelmä. Varausjärjestelmän kautta voidaan tehdä, tallentaa, poistaa ja muokata tehtyjä mökkivarauksia.

Projektin ovat tuottaneet Juho Leppälä, Tomi Nissinen, Ville Lehikoinen ja Brendon komulainen. 

## Käyttöohjeet
Ohjelman ajaminen tapahtuu Mokinvaraus.java tiedostosta.
Ohjelman tietokantana toimii MySQL 8.0.25 palvelin, ja ohjelman kirjautumistiedot
tietokantaan ovat muokattavissa Tietokanta.java luokasta. Tietokannan pohjana toimii mokinvaraus.sql tiedosto.

Ajettuasi ohjelman, aukeaa kirjautumisnäkymä. Kirjautumistiedot ovat seuraavanlaiset: 
Käyttäjätunnus = mokkikodit ja salasana = 123456.
Syötettyäsi kirjautumistiedot, paina "Kirjaudu"-nappia. Tämän jälkeen sinulle aukeaa viisi välilehteä: 
Varauksen teko, Varausten hallinta, Mökkien hallinta, Laskujen hallinta ja seuranta, Majoittumisen raportointi.
Niiden toiminta on kuvattu alla:

### Varauksen teko -välilehti:
Välilehdellä on textfieldit, jotka täytetään normaaliin tuttuun tapaan. Etunimi, sukunimi, puhelinnumero, osoite, sähköposti, 
yritys (jos on) ja henkilömäärä ovat textfieldejä. Toiveet ovat textarea. Puolestaan vuokrauksen ajankohta valitaan datepickereiden avulla ja mökki comboboxilla.
Kun kentät on täytetty, painetaan "LUO VARAUS JA LASKU"-nappia. Tämä luo nimensämukaisesti laskun ja varauksen seuraaville välilehdille.

### Varausten hallinta -välilehti:
Tällä välilehdellä voit muokata tehtyjä varauksia (henkilömäärä, varauksen kesto, toiveet ja mökki). 
Ottaaksesi varauksen muokattavaksi, täytyy haluttua varausta tuplaklikata. Voit muokata tilauksen kestoa, toiveita, henkilömäärää ja mökkiä. 
Etunimeä, sukunimeä, puhelinnumeroa, osoitetta, sähköpostia tai yritystä ei voi muokata. Muokattuasi kenttiä, paina "TALLENNA"-nappia, jolloin tekemäsi 
muutokset tallentuvat varaukseen. Jos haluat poistaa varauksen, tuplaklikkaa haluaamasi varausta ja paina "PERU/POISTA"-nappia.

### Mökkien hallinta -välilehti:
Tällä välilehdellä voit selata mökkejä ja tehdä niihin muutoksia. Tuplaklikkaamalla mökkiä, otat sen muokattavaksi. 
Painamalla tallenna-nappia, syötetyt uudet tiedot tallentuu. Poista-nappi puolestaan luonnollisesti poistaa valitun mökin.

### Laskujen hallinta -välilehti:
Tällä välilehdellä näet kaikki tämän hetkiset laskut. Laskuja voit muokata sen mukaan, mikä
mökki on kyseessä (hinnat vaihtelevat mökkien välillä), paljonko sen vuorokausihinta on, kuinka kauan mökki on varattuna. 
Näistä koostuu kokonaishinta, jota voi vielä muokata tällä välilehdellä, jos kyseessä onkin jonkinlainen tarjous 
tai sopimus hinnasta (esimerkiksi pidempiaikainen vuokraus voisi olla huokeampaa.)

### Majoittumisen raportointi -välilehti:
Välilehdellä näkyy tilastotietoja tehdyistä tilauksista, niiden määristä
ja tilausten tuottamista tuloista. Tilastoja on merkitty niin kokonaisvaltaisesti kuin mökkikohtaisestikin.
