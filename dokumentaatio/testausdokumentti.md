# Testausdokumentti

15-pelin ratkaisu on vaativa tehtävä. Sitä ei ole mahdollista ratkaista luotettavasti tavallisilla hakualgoritmeilla, jotka sokeasti pyrkivät käymään kaikki verkon solmut läpi. Koska pelilaudalla on niin monta mahdollista permutaatiota, muisti voi loppua ennen ratkaisun löytymistä. Lisäksi pahimmassa tapauksessa ratkaisuaika olisi järkyttävän suuri. 15-pelin ratkaiseminen vaatii siis keinoja, jotka ohjaavat hakualgoritmiä oikeaan suuntaan. Tätä kutsutaan heuristiikkafunktioksi ja sillä on suuri vaikutus ratkaisun löytämisnopeuteen. Näistä syistä johtuen tämä projekti keskittyy mahdollisimman nopean ja luotettavasti nopean ratkaisualgoritmin kehittämiseen. Tavoitteena on siis saada sekä keskimääräinen aika että pahimman tapauksen aika mahdollisimman pieneksi.

Sovelluksen sisältämää A*-algoritmia voi testata ajamalla ohjelman. Sovellus tulostaa konsoliin vaihtoehtoja. Ensimmäinen vaihtoehto ratkaisee yhden laudan, jonka jälkeen on mahdollista tulostaa algoritmin löytämä ratkaisu. Näin voidaan nähdä, että pelilauta on oikeasti ratkaistu. Kun tiedetään, että sovellus osaa oikeasti ratkaista lautoja, voidaan siirtyä toiseen vaihtoehtoon, joka ratkaisee annetun määrän pelilautoja. Sovellusta on helppo käyttää seuraamalla sen tulostamia ohjeita ja voit toistaa nämä algoritmin ratkaisuaikatestit itse ajamalla ohjelman. Tämän jälkeen se tulostaa ratkaisuista saatuja statistiikkoja, kuten suurin, pienin ja keskimääräinen ratkaisuaika. Alla näkyvässä kuvassa esitettynä saadut ratkaisuajat sekuntteina kun ohjelma ratkaisi tuhat satunnaista pelilautaa.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/size_4-1000_solves_graph.png "Ratkaisuajat koolla 4 1000 satunnaisella pelilaudalla")

Lisäksi 10000 satunnaisen laudan ratkaisusta saadut tulokset, jotka ohjelma on tulostanut.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/size_4-10000_solves.png "Testitulokset koolla 4 10000 satunnaisella pelilaudalla")

Sovelluksella voi myös ratkaista muita n-pelejä. Alla olevassa kuvassa tulokset 10000 satunnaiselle pelilaudalle, kun laudan koko on 3\*3. Algoritmi on selvästi hyvin nopea näin pienellä laudalla, sillä keskimääräinen ratkaisuaika on jossain alle millisekuntin kohdilla.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/size_3-10000_solves.png "Testitulokset 10000 satunnaisella pelilaudalla")

Lisäksi alla ratkaisuajat 100 satunnaiselle pelilaudalle, kun laudan koko on 5\*5. Voidaan huomata, että laudan koon kasvattaminen kasvattaa huomattavasti vaadittavaa ratkaisuaikaa.

![kuva](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/kuvat/size_5-1000_solves.png "Testitulokset 10000 satunnaisella pelilaudalla")

Algoritmin testauksen lisäksi sovellus sisältää yksikkötestejä, jotka voidaan ajaa konsolikomennolla `mvn test`. Yksikkötesteillä on varmistettu, että algoritmin ja sovelluksen eri osat toimivat. Testit ovat kattavia, mutta pari asiaa vaativat, että käyttäjä tarkistaa niiden toiminnat ajamalla ohjelman. Esimerkiksi `toString()`-metodit ja se, että sekoitettu lauta on todellisesti satunnainen, eivät selviä yksikkötesteistä, vaikka pelilaudan sekoittamista on testattu muuten hyvin. On myös pieni mahdollisuus, että satunnaisuudesta johtuen kaikkia haaroja ei käydä testauksessa läpi. Esimerkiksi jos lauta on liian helppo ratkaista, maksimi listan pituutta ei saavuteta.