package patientenverwaltung.datalayer.services;

import java.util.Map;

import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.models.DataSource;
import patientenverwaltung.configuration.models.DataSources;
import patientenverwaltung.configuration.models.dbconnection.DbConnection;
import patientenverwaltung.configuration.models.dbconnection.DbConnections;
import patientenverwaltung.configuration.models.enums.ConnectionType;
import patientenverwaltung.configuration.models.enums.FileType;
import patientenverwaltung.configuration.models.enums.ModelType;
import patientenverwaltung.configuration.models.fileconnection.File;
import patientenverwaltung.configuration.models.fileconnection.FileConnection;
import patientenverwaltung.configuration.models.fileconnection.FileConnections;
import patientenverwaltung.datalayer.dataaccessobjects.IDao;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.AbstractDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.LeistungDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.PatientDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.PflegekraftDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.AbstractDaoFile;
import patientenverwaltung.datalayer.exceptions.DaoException;

public class DataLayerFactory {

    private static Configuration config;

    public static IDataLayer createDataLayer(Configuration configuration) throws DaoException {
        config = configuration;

        DataLayer dataLayer = new DataLayer();
        dataLayer.setDaoLeistung(createDao(ModelType.LEISTUNG));
        dataLayer.setDaoPflegekraft(createDao(ModelType.PFLEGEKRAFT));
        dataLayer.setDaoPatient(createDao(ModelType.PATIENT));

        return dataLayer;
    }

    private static <T, ID> IDao<T, ID> createDao(ModelType modelType) throws DaoException {
        DataSource dataSource = getDataSource(modelType);

        return switch (dataSource.getSource()) {
            case DB -> createDbDao(modelType, dataSource);
            case FILE -> createFileDao(modelType, dataSource);
        };
    }

    private static <T, ID> IDao<T, ID> createDbDao(ModelType modelType, DataSource dataSource) throws DaoException {
        DbConnection dbConnection = getDbConnection(dataSource);

        switch (modelType) {
            case LEISTUNG -> {
                if (dbConnection.getType() == ConnectionType.SQLITE) {
                    return (AbstractDaoSqlite<T, ID>) new LeistungDaoSqlite(dbConnection.getUrl());
                }

                throw new DaoException("Unsupported DB connection type for LEISTUNG: " + dbConnection.getType());
            }
            case PFLEGEKRAFT -> {
                if (dbConnection.getType() == ConnectionType.SQLITE) {
                    return (AbstractDaoSqlite<T, ID>) new PflegekraftDaoSqlite(dbConnection.getUrl());
                }

                throw new DaoException("Unsupported DB connection type for PFLEGEKRAFT: " + dbConnection.getType());
            }
            case PATIENT -> {
                if (dbConnection.getType() == ConnectionType.SQLITE) {
                    return (AbstractDaoSqlite<T, ID>) new PatientDaoSqlite(dbConnection.getUrl());
                }

                throw new DaoException("Unsupported DB connection type for PATIENT: " + dbConnection.getType());
            }
            default -> throw new IllegalArgumentException("Unsupported model type for DB: " + modelType);
        }
    }

    private static <T, ID> IDao<T, ID> createFileDao(ModelType modelType, DataSource dataSource) {
        FileConnection fileConnection = getFileConnection(modelType);
        Map<FileType, File> fileMap = fileConnection.createFileMap();

        // add the file type here and create the correct persistence service
        // map to the correct modelType => Leistung, Pflegekraft, Patient
        switch (modelType) {
            case LEISTUNG -> {

            }
            case PFLEGEKRAFT -> {
            }
            case PATIENT -> {
            }
            default -> throw new IllegalArgumentException("Unsupported model type for FILE: " + modelType);
        }
    }

    private static DataSource getDataSource(ModelType modelType) {
        DataSources dataSources = config.getDataSources();
        DataSource dataSource = dataSources.createDataSourceMap().get(modelType);

        if (dataSource == null) {
            throw new IllegalArgumentException("No DataSource found for model type: " + modelType);
        }

        return dataSource;
    }

    private static DbConnection getDbConnection(DataSource dataSource) {
        DbConnections dbConnections = config.getConnections().getDbConnections();
        DbConnection dbConnection = dbConnections.getDbConnectionMap().get(dataSource.getType());

        if (dbConnection == null) {
            throw new IllegalArgumentException("No DbConnection found for type: " + dataSource.getType());
        }

        return dbConnection;
    }

    private static FileConnection getFileConnection(ModelType modelType) {
        FileConnections fileConnections = config.getConnections().getFileConnections();
        FileConnection fileConnection = fileConnections.createFileConnectionMap().get(modelType);

        if (fileConnection == null) {
            throw new IllegalArgumentException("No FileConnection found for model type: " + modelType);
        }

        return fileConnection;
    }
}
