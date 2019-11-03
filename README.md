# 15-pelin ratkaisija

## Dokumentaatio:
- [Määrittelydokumentti](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/määrittelydokumentti.md)
- [Viikkoraportit](https://github.com/pyigyli/15-puzzle-solver/tree/master/dokumentaatio/viikkoraportit):
  - [Viikkoraportti 1](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-1.md)
  - [Viikkoraportti 2](https://github.com/pyigyli/15-puzzle-solver/blob/master/dokumentaatio/viikkoraportit/viikko-2.md)

## Komentorivitoiminnot
Komentorivin komennot suoritetaan kansiossa *ConnectFour3D*.

### Testit
Testit suoritetaan komennolla `mvn test`.

Testikattavuusraportti luodaan komennolla `mvn jacoco:report`.
Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*.

### Suoritettava jar-tiedosto
Suoritettavan jar-tiedoston voi luoda komennolla `mvn package`.
Luodun tiedoston polku on *target/ConnectFour3D-1.0-SNAPSHOT.jar*.

### JavaDoc
JavaDoc generoidaan komennolla `mvn javadoc:javadoc`.
JavaDocia voi tarkastella avaamalla tiedosto *target/site/apidocs/index.html*.

### Checkstyle
Tiedostoon [checkstyle.xml](https://github.com/pyigyli/ot-harjoitustyo/blob/master/harjoitustyo/ConnectFour3D/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla `mvn jxr:jxr checkstyle:checkstyle`.
Määriteltyjen tarkistusten rikkeet ovat tarkasteltavissa avaamalla tiedosto *target/site/checkstyle.html*.