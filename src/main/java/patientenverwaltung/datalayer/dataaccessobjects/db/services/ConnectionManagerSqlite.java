package patientenverwaltung.datalayer.dataaccessobjects.db.services;

public class ConnectionManagerSqlite extends ConnectionManager {
    private boolean classLoaded = false;

    public ConnectionManagerSqlite(String connectionString) {
        super("ConnectionManagerSqlite", connectionString);

    }


}
