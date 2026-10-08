# Patientenverwaltung

Java-Lernprojekt für eine austauschbare Persistenzschicht. Patienten,
Pflegekräfte und Leistungen werden über ein gemeinsames DAO-Interface aus
SQLite, XML oder CSV gelesen und bearbeitet. JAXB und XSD-Dateien beschreiben
die XML-Konfiguration; OpenCSV übernimmt die CSV-Verarbeitung.

Die Anwendung ist eine Konsolendemonstration, keine fertige Verwaltungssoftware.
Beim Start liest sie die Datensätze und **ändert jeweils den ersten Datensatz**
als Update-Beispiel. Verwende dafür ausschließlich Kopien von Beispieldaten.

## Starten

Voraussetzungen: JDK 21 und Maven 3.9+. Alle Befehle im Repository-Verzeichnis
ausführen, da die Konfiguration relative Dateipfade verwendet.

```bash
mvn package
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=patientenverwaltung.Main
```

## Datenquellen

Die [appconfig.xml](src/main/resources/files/appconfig.xml) legt die Datenquelle
pro Modell fest. Die aktuelle Konfiguration verwendet:

| Modell | Quelle | Datei |
| --- | --- | --- |
| Patient | SQLite | `src/main/resources/files/Patientenverwaltung.db3` |
| Pflegekraft | XML | `src/main/resources/files/Pflegekraft.xml` |
| Leistung | CSV | `src/main/resources/files/Leistungen.csv` |

Dateinamen sind auf Linux groß-/kleinschreibungsabhängig. Weitere konfigurierbare
Patienten-Dateiquellen sind nicht als Beispieldateien enthalten und müssen vor
einem Wechsel von SQLite angelegt werden.

## Aufbau und Prüfung

- [configuration](src/main/java/patientenverwaltung/configuration): XML-Konfiguration und Datenquellenauswahl.
- [datalayer](src/main/java/patientenverwaltung/datalayer): DAO-Interfaces, Factory und Persistenzimplementierungen.
- [models](src/main/java/patientenverwaltung/models): Fachmodelle.
- [XSD-Schemas](src/main/resources/files/xsds): Struktur der XML-Dateien.

`mvn verify` prüft den Build. Es sind derzeit keine automatisierten Tests
enthalten. Echte Patienten- oder Personaldaten gehören nicht in dieses
Lernprojekt; die Herkunft der vorhandenen Beispieldaten ist separat zu prüfen.
