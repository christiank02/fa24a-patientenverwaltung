package patientenverwaltung.models;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patient", propOrder = {"id", "vorname", "nachname", "geburtsdatum",
        "pflegegrad", "zimmer", "vermoegen"})
public class Patient {

    @XmlElement(name = "id")
    @CsvBindByName(column = "id")
    private long id;

    @XmlElement(name = "vorname")
    @CsvBindByName(column = "vorname")
    private String vorname;

    @XmlElement(name = "nachname")
    @CsvBindByName(column = "nachname")
    private String nachname;

    @XmlElement(name = "geburtsdatum")
    @CsvBindByName(column = "geburtsdatum")
    private LocalDate geburtsdatum;

    @XmlElement(name = "pflegegrad")
    @CsvBindByName(column = "pflegegrad")
    private int pflegegrad;

    @XmlElement(name = "zimmer")
    @CsvBindByName(column = "zimmer")
    private String zimmer;

    @XmlElement(name = "vermoegen")
    @CsvBindByName(column = "vermoegen")
    private double vermoegen;

    public Patient() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getPflegegrad() {
        return pflegegrad;
    }

    public void setPflegegrad(int pflegegrad) {
        this.pflegegrad = pflegegrad;
    }

    public String getZimmer() {
        return zimmer;
    }

    public void setZimmer(String zimmer) {
        this.zimmer = zimmer;
    }

    public double getVermoegen() {
        return vermoegen;
    }

    public void setVermoegen(double vermoegen) {
        this.vermoegen = vermoegen;
    }
}
