# Viikkoraportti 3
---
Otin A* algoritmini käyttämän PriorityQueue objectin ja korvasin sen itse luomallani tietorakenteella. Luomani prioriteettilista toimii linkatun listan tavoin, jossa uusi elementti käy listan alusta alkaen elementtejä läpi siihen asti, että sen jälkeinen elementti ei ole pelilaudan heuristiikka-arvoltaan pienempi. Toteutin myös tähän liittyvät Unit-testit ja Javadocsit. Algoritmini toimi PriorityQueuea käyttämällä jatkuvasti alle 0.15 sekunnin ajassa, mutta omalla toteutuksellani aika voi vaihdella likimain nollasta jopa yli 10 sekunttiin. Todennäköisesti parantelen toteutusta myöhemmin.

Käytetty aika tiistaina: 4 tuntia

---
Lisäsin myös suljetulle listalle itse rakentamani tietorakenteen. En ole varma mitä tiistaina luomalle rakenteelle tapahtui, mutta pelin ratkaisu on nyt erittäin nopea. Pahimmassa tapauksessa ratkaisu on kestänyt kaksi sekunttia, mutta yli 95 % on reilusti alle sekunnin aikaluokkaa. Toteutin myös testit, dokumentoinnin ja varmistin checkstylen. Hieman hämmentynyt, mutta tyytyväinen tähän ratkaisunopeuteen.

Käytetty aika torstaina: 2 tuntia

---
Käytetty aika yhteensä viikon aikana: 6
-