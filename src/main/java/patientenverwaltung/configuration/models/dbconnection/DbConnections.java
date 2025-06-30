package patientenverwaltung.configuration.models.dbconnection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import patientenverwaltung.configuration.models.enums.ConnectionType;

public class DbConnections {

    private List<DbConnection> dbConnections;

    public List<DbConnection> getDbConnections() {
        return dbConnections;
    }

    public Map<ConnectionType, DbConnection> getDbConnectionMap() {
        return dbConnections.stream()
                .collect(Collectors.toMap(DbConnection::getType, dbConnection -> dbConnection));
    }

}
