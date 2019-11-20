# Testausdokumentti

Sovelluksen sisältämää A*-algoritmia voi testata ajamalla ohjelman. Sovellus tulostaa konsoliin vaihtoehtoja. Ensimmäinen vaihtoehto ratkaisee yhden laudan, jonka jälkeen on mahdollista tulostaa algoritmin löytämä ratkaisu. Näin voidaan nähdä, että pelilauta on oikeasti ratkaistu. Kun tiedetään, että sovellus osaa oikeasti ratkaista lautoja, voidaan siirtyä toiseen vaihtoehtoon, joka ratkaisee annetun määrän pelilautoja. Sovellusta on helppo käyttää seuraamalla sen tulostamia ohjeita ja voit toistaa nämä algoritmin ratkaisuaikatestit itse ajamalla ohjelman. Tämän jälkeen se tulostaa ratkaisuista saatuja statistiikkoja, kuten suurin, pienin ja keskimääräinen ratkaisuaika. Alla näkyvässä kuvassa esitettynä saadut ajat tuhannesta satunnaisesta laudasta.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/1000_solve_graph.png "Ratkaisuajat 1000 satunnaisella pelilaudalla")

Lisäksi 10000 satunnaisen laudan ratkaisusta saadut tulokset, jotka ohjelma on tulostanut.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/10000_solve_results.png "Testitulokset 10000 satunnaisella pelilaudalla")

Algoritmin testauksen lisäksi sovellus sisältää yksikkötestejä, jotka voidaan ajaa konsolikomennolla `mvn test`. Yksikkötesteillä on varmistettu, että algoritmin ja sovelluksen eri osat toimivat. Testit ovat kattavia, mutta pari asiaa vaativat, että käyttäjä tarkistaa niiden toiminnat ajamalla ohjelman. Esimerkiksi `toString()`-metodit ja se, että sekoitettu lauta on todellisesti satunnainen.