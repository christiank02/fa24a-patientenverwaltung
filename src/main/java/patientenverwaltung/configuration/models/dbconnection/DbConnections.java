package patientenverwaltung.configuration.models.dbconnection;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import patientenverwaltung.configuration.models.enums.DbConnectionType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class DbConnections {

    @XmlElement(name = "dbConnection")
    private List<DbConnection> dbConnections;

    public List<DbConnection> getDbConnections() {
        return dbConnections;
    }

    public Map<DbConnectionType, DbConnection> getDbConnectionMap() {
        return dbConnections.stream()
                .collect(Collectors.toMap(DbConnection::getType, dbConnection -> dbConnection));
    }

}
