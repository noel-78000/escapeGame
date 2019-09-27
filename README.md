## Escape game for GAMEPLAY STUDIO

This application can be run by main method in Main class

## How to compile with maven

* __install maven on your system__<br />
  You could find help here: https://maven.apache.org/install.html

* __compile:__
  mvn clean install dependency:copy-dependencies -DoutputDirectory=target/lib -Dmaven.test.skip=true -PuseExternalConfigFiles<br />

* __run the application:__
  java -cp conf:lib:./target/escapeGame-1.0-SNAPSHOT.jar com.ocr.noel.escapeGame.Main
