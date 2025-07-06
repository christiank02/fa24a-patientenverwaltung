package patientenverwaltung.configuration.models.dbconnection;

import jakarta.xml.bind.annotation.*;
import patientenverwaltung.configuration.models.enums.DbConnectionType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DbConnection {

    @XmlAttribute
    private DbConnectionType type;

    @XmlElement(name = "url")
    private String url;

    public DbConnectionType getType() {
        return type;
    }

    public void setType(DbConnectionType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
