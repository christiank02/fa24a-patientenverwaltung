package patientenverwaltung.datalayer.dataaccessobjects.db.services;

import patientenverwaltung.datalayer.exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public abstract class ConnectionManager {
    private final String className;
    private final String connectionString;
    private Connection existingConnection;

    public ConnectionManager(String className, String connectionString) {
        this.className = className;
        this.connectionString = connectionString;
    }

    public String getClassName() {
        return className;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public Connection getExistingConnection() {
        return existingConnection;
    }

    public Connection getNewConnection() throws DaoException {
        if(Objects.isNull(connectionString)) {
            throw new DaoException("No connection string provided!");
        }

        try {
            Connection con = DriverManager.getConnection(connectionString);
            this.existingConnection = con;

            return con;
        } catch (SQLException e) {
            throw new DaoException("Could not establish connection to the database!");
        }
    }
}
