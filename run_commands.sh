#!/bin/sh
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ${DIR}/15-puzzle-solver
echo Generoidaan JavaDocs-sivuja...
mvn javadoc:javadoc
echo Generoidaan testikattavuusraporttia...
mvn jacoco:report
echo Generoidaan checkstyle-raporttia...
mvn jxr:jxr checkstyle:checkstyle
echo Ajetaan testit...
mvn test
echo Paina enter-näppäintä sulkeaksesi tämän ikkunan.
read