package patientenverwaltung.models;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.*;


@XmlRootElement(name = "leistung")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "leistung", propOrder = {"bezeichnung", "beschreibung"})
public class Leistung {

    @XmlAttribute(name = "lkNr")
    @CsvBindByName(column = "lkNr")
    private String lkNr;

    @XmlElement(name = "bezeichnung")
    @CsvBindByName(column = "bezeichnung")
    private String bezeichnung;

    @XmlElement(name = "beschreibung")
    @CsvBindByName(column = "beschreibung")
    private String beschreibung;

    public Leistung() {
    }

    public String getLkNr() {
        return lkNr;
    }

    public void setLkNr(String lkNr) {
        this.lkNr = lkNr;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
