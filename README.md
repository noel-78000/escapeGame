## Escape game for GAMEPLAY STUDIO

This application can be run by main method in Main class

## How to compile with maven

* __install maven on your system__

__Help for linux here:__

First: install java<br />
After install maven as below:<br />
  wget http://mirrors.estointernet.in/apache/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz<br />
  tar -xzf  apache-maven-3.6.2-bin.tar.gz<br />
  sudo mkdir /opt/maven<br />
  sudo mv apache-maven-3.6.2 /opt/maven<br />
  add this env variable: M2_HOME="/opt/maven/apache-maven-3.6.2"<br />

* __run the application:__
  compile with: mvn install <br />
  java -cp ./target/escapeGame-1.0-SNAPSHOT.jar com.ocr.noel.escapeGame.Main
