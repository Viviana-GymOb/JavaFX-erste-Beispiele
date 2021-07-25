# JavaFX: erste Beispiele
Anhand dieser drei einfacher Beispiele kann man die erste Anwendungen in JavaFX kennenlernen.

Nachdem das entsprechende Paket installiert wird, kann ein neues Projekt in IntelliJ  erzeugt werden. Hier sollen die nächsten zwei Schritte unbedingt beachtet werden:
-	die path der Verzeichnis, die die JavaFX Bibliothek enthält, soll unter
 File -> Project Structure -> Libraries 
angegeben werden;
-	die Virtual Maschine Optionen sollen angeschaltet (unter Run/Edit Configurati-ons) und das Argument: 
--module-path path\javafx-sdk-11.0.2\lib  
--add-modules javafx.controls,javafx.fxml
soll eingegeben werden, wobei path dem Pfad der Verzeichnis entspricht, in dem die Bibliothek gespeichert wird (dieser Schritt ist für jede main-Datei aus-zuführen).
In der IDE Eclipse soll das gleiche Argument unter Run Configurations/VM Arguments eingegeben werden.

