package patientenverwaltung.datalayer.dataaccessobjects.db.services;

public class ConnectionManagerSqlite extends ConnectionManager {

    public ConnectionManagerSqlite(String connectionString) {
        super("ConnectionManagerSqlite", connectionString);
    }


}
