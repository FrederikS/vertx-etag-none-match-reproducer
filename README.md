## Steps to reproduce

1. `./gradlew run`
2. `curl -v 'http://localhost:8080/check/e-tag' -H 'IF-NONE-MATCH: "1234"'`
3. `curl -v 'http://localhost:8080/check/e-tag' -H 'IF-NONE-MATCH: "1234",'`
