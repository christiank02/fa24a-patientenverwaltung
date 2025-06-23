package patientenverwaltung.models;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "pflegekraft")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pflegekraft", propOrder = {"id", "vorname", "nachname", "telefon"})
public class Pflegekraft {

    @XmlElement(name = "id")
    @CsvBindByName(column = "id")
    private long id;

    @XmlElement(name = "vorname")
    @CsvBindByName(column = "vorname")
    private String vorname;

    @XmlElement(name = "nachname")
    @CsvBindByName(column = "nachname")
    private String nachname;

    @XmlElement(name = "telefon")
    @CsvBindByName(column = "telefon")
    private String telefon;

    public Pflegekraft() {}

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
