package patientenverwaltung.datalayer.services;

import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.models.DataSource;
import patientenverwaltung.configuration.models.dbconnection.DbConnection;
import patientenverwaltung.configuration.models.enums.DbConnectionType;
import patientenverwaltung.configuration.models.enums.FileType;
import patientenverwaltung.configuration.models.enums.ModelType;
import patientenverwaltung.configuration.models.fileconnection.File;
import patientenverwaltung.configuration.models.fileconnection.FileConnection;
import patientenverwaltung.datalayer.dataaccessobjects.IDao;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.AbstractDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.LeistungDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.PatientDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.db.daos.PflegekraftDaoSqlite;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.AbstractDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.LeistungDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.PatientDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.PflegekraftDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceServiceCsv;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceServiceXml;
import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.models.Leistung;
import patientenverwaltung.models.Patient;
import patientenverwaltung.models.Pflegekraft;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class DataLayerFactory {

    private static Configuration config;

    public static IDataLayer createDataLayer(Configuration configuration) throws DaoException {
        config = configuration;

        DataLayer dataLayer = new DataLayer();
        dataLayer.setDaoLeistung(createDao(ModelType.leistung));
        dataLayer.setDaoPflegekraft(createDao(ModelType.pflegekraft));
        dataLayer.setDaoPatient(createDao(ModelType.patient));

        return dataLayer;
    }

    private static <T, ID> IDao<T, ID> createDao(ModelType modelType) throws DaoException {
        DataSource dataSource = getDataSource(modelType);
        System.out.println("===== Creating DAO for model type: " + modelType + " =====");
        System.out.println(dataSource.getSource()); // db or file
        System.out.println(dataSource.getType());

        return switch (dataSource.getSource()) {
            case db -> createDbDao(modelType, dataSource);
            case file -> createFileDao(modelType, dataSource);
        };
    }

    private static <T, ID> IDao<T, ID> createDbDao(ModelType modelType, DataSource dataSource) throws DaoException {
        String dbConnectionType = dataSource.getType().name();
        DbConnection dbConnection = getDbConnection(dbConnectionType);
        String dbUrl = dbConnection.getUrl();

        if (Objects.requireNonNull(dbConnection.getType()) == DbConnectionType.sqlite) {// Implement database-specific DAO creation logic here
            System.out.println("Creating DAO for DB type: " + dbConnection.getType());
            switch (modelType) {
                case leistung -> {
                    return (AbstractDaoSqlite<T, ID>) new LeistungDaoSqlite(dbUrl);
                }
                case pflegekraft -> {
                    return (AbstractDaoSqlite<T, ID>) new PflegekraftDaoSqlite(dbUrl);
                }
                case patient -> {
                    return (AbstractDaoSqlite<T, ID>) new PatientDaoSqlite(dbUrl);
                }
            }
        }
        throw new DaoException("Unsupported database connection type: " + dbConnection.getType());
    }

    private static <T, ID> IDao<T, ID> createFileDao(ModelType modelType, DataSource dataSource) throws DaoException {
        String type = dataSource.getType().name();
        FileType fileType = FileType.valueOf(type);
        FileConnection fileConnection = getFileConnection(modelType);
        File daoFile = fileConnection.getFileList().stream()
                .filter(file -> file.getType() == fileType)
                .findFirst()
                .orElseThrow(() -> new DaoException("File connection not found for type: " + fileType));
        String filePath = daoFile.getValue();

        System.out.println("Creating DAO for File type: " + fileType + " with file: " + daoFile.getValue());
        switch (daoFile.getType()) {
            case xml -> {
                switch (modelType) {
                    case leistung -> {
                        FilePersistenceServiceXml<Leistung> filePersistenceService = new FilePersistenceServiceXml<>();
                        return (AbstractDaoFile<T, ID>) new LeistungDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    case pflegekraft -> {
                        FilePersistenceServiceXml<Pflegekraft> filePersistenceService = new FilePersistenceServiceXml<>();
                        return (AbstractDaoFile<T, ID>) new PflegekraftDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    case patient -> {
                        FilePersistenceServiceXml<Patient> filePersistenceService = new FilePersistenceServiceXml<>();
                        return (AbstractDaoFile<T, ID>) new PatientDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    default -> throw new DaoException("Unsupported model type for XML: " + modelType);
                }
            }
            case csv -> {
                switch (modelType) {
                    case leistung -> {
                        FilePersistenceServiceCsv<Leistung> filePersistenceService = new FilePersistenceServiceCsv<>(';');
                        return (AbstractDaoFile<T, ID>) new LeistungDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    case pflegekraft -> {
                        FilePersistenceServiceCsv<Pflegekraft> filePersistenceService = new FilePersistenceServiceCsv<>(';');
                        return (AbstractDaoFile<T, ID>) new PflegekraftDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    case patient -> {
                        FilePersistenceServiceCsv<Patient> filePersistenceService = new FilePersistenceServiceCsv<>(';');
                        return (AbstractDaoFile<T, ID>) new PatientDaoFile(filePersistenceService, Path.of(filePath));
                    }
                    default -> throw new DaoException("Unsupported model type for CSV: " + modelType);
                }
            }
        }

        throw new DaoException("Unsupported file type: " + daoFile.getType());
    }

    private static DataSource getDataSource(ModelType modelType) {
        return config.getDataSources().createDataSourceMap().get(modelType);
    }

    private static DbConnection getDbConnection(String dbConnectionType) {
        DbConnectionType type = DbConnectionType.valueOf(dbConnectionType);
        Map<DbConnectionType, DbConnection> dbConnectionMap = config.getConnections().getDbConnections().getDbConnectionMap();
        return dbConnectionMap.get(type);
    }

    private static FileConnection getFileConnection(ModelType modelType) {
        Map<ModelType, FileConnection> fileConnectionMap = config.getConnections().getFileConnections().createFileConnectionMap();
        return fileConnectionMap.get(modelType);
    }
}