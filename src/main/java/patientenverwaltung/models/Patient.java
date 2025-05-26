package patientenverwaltung.models;

import java.time.LocalDate;

public class Patient {

    private long id;
    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private int pflegegrad;
    private String zimmer;
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
