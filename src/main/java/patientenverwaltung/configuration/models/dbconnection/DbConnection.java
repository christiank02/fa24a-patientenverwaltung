package patientenverwaltung.configuration.models.dbconnection;

import patientenverwaltung.configuration.models.enums.ConnectionType;

public class DbConnection {

    private ConnectionType type;
    private String url;

    public ConnectionType getType() {
        return type;
    }

    public void setType(ConnectionType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
