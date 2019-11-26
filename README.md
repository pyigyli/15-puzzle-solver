# 15-pelin ratkaisija

## Dokumentaatio:
- [Määrittelydokumentti](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/määrittelydokumentti.md)
- [Toteutusdokumentti](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/toteutusdokumentti.md)
- [Testausdokumentti](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/testausdokumentti.md)
- [Viikkoraportit](https://github.com/pyigyli/15-puzzle-solver/tree/master/dokumentaatio/viikkoraportit):
  - [Viikkoraportti 1](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-1.md)
  - [Viikkoraportti 2](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-2.md)
  - [Viikkoraportti 3](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-3.md)
  - [Viikkoraportti 4](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-4.md)
  - [Viikkoraportti 5](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-5.md)

## Komentorivitoiminnot

JavaDocs, testikattavuusraportti, checkstyleraportti, jar-tiedosto ja testit voidaan generoida ja ajaa suorittamalla reposition juurikansiosta löytyvä shell-skripti `run_commands.sh`.

Yksittäin ajettuna kaikki komentorivikomennot suoritetaan Java-projektin kansiossa.

### Testit
Testit suoritetaan komennolla `mvn test`.

Testikattavuusraportti luodaan komennolla `mvn jacoco:report`.

Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*.

### Suoritettava jar-tiedosto
Suoritettavan jar-tiedoston voi luoda komennolla `mvn package`.

Luodun tiedoston polku on *target/15-puzzle-solver-1.0-SNAPSHOT*.

Sovellus vaatii konsolia tekstin esittämiseen, joten IDE:n
käyttäminen ohjelman suorittamiseen voi olla helpompi vaihtoehto.

### JavaDoc
JavaDoc generoidaan komennolla `mvn javadoc:javadoc`.

JavaDocia voi tarkastella avaamalla tiedosto *target/site/apidocs/index.html*.

### Checkstyle
Tiedostoon [checkstyle.xml](https://github.com/pyigyli/15-puzzle-solver/blob/master/15-puzzle-solver/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla `mvn jxr:jxr checkstyle:checkstyle`.
Määriteltyjen tarkistusten rikkeet ovat tarkasteltavissa avaamalla tiedosto *target/site/checkstyle.html*.