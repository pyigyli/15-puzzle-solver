# Viikkoraportti 2
---
Toteutin 15-pelin sekoittajan. Nyt nappia painamalla sovellus sekoittaa pelilaudan silmän räpäyksessä. Myöhemmin saman päivän aikana lisäsin projektiini javadocsit, checkstylen ja testikattavuusraporttien luomisen. Loin myös yhden automaattisesti generoidun testiluokan, mutten toteuttanut vielä yhtäkään testiä.

Käytetty aika sunnuntaina: 4 tuntia

---
Yritin löytää tapoja luoda unit-testejä sovelluksen visuaalisille elementeille, mutta mikään ei onnistunut. Kulutin turhaa aikaa canvas-elementin testaamiseen, joka ei käytännössä testannut juuri mitään, joten pyyhin lopulta nämä testit pois. Sovelluksen käyttöliittymä ei onneksi edes ole kovin monimutkainen. Tein yksikkötestit algoritmiluokalle niille metodeille, jotka olen tähäne mennessä saanut valmiiksi.

Käytetty aika maanantaina: 3 tuntia

---
Kehitin jonkin aikaa IDDFS-algoritmia, jonka jälkeen löysin kurssin ohjaajan jättämän palautteen ensimmäisen viikon palautuksesta ja aloin tutkimaan A* algoritmia.

Käytetty aika tiistaina: 3 tuntia

---
Toteutin A* algoritmin. Tällä hetkellä käytän heuristiikan arvioimiseen solmun syvyyttä, Manhattan etäisyyttä ja Hammington etäisyyttä. Kokeilin myös lineaarikonfliktien löytämistä, mutta tämä ei näyttänyt tekevän algoritmista yhtään sen nopeampaa. Dokumentaatio, testit ja funktioiden luettavuuden optimointi jäivät tänään täysin huomioimatta, joten niitä pitää korjata myöhemmin.

Käytetty aika keskiviikkona: 8 tuntia

---
Viimeiset bugikorjaukset A* algoritmin kanssa, jonka jälkeen kulutin ihan liian kauan aikaa javaFX-härveleiden kanssa. Halusin kovasti visuaalista toteutusta sille, kun algoritmi löytää 15-pelin ratkaisun ja liikuttaa palat yksitellen nopeasti paikalleen. Oli myöhä, joten menin nukkumaan.

Käytetty aika torstaina: 7 tuntia

---
Toteutin tekstipohjaisen konsolosovelluksen ja parantelin koodin luettavuutta, lisäsin javadocsit ja sain kaikki unit-testit kuntoon. Checkstylet on hoidettu ja A* algoritmi toimii järkyttävän nopeasti.

Käytetty aika perjantaina: 6 tuntia

---
Käytetty aika yhteensä viikon aikana: 31
-