package patientenverwaltung.datalayer.services;

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
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.LeistungDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.PatientDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.daos.PflegekraftDaoFile;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceServiceCsv;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceServiceXml;
import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.models.Leistung;
import patientenverwaltung.models.Patient;
import patientenverwaltung.models.Pflegekraft;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

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

        return switch (dataSource.getSource()) {
            case db -> createDbDao(modelType, dataSource);
            case file -> createFileDao(modelType);
        };
    }

    private static <T, ID> IDao<T, ID> createDbDao(ModelType modelType, DataSource dataSource) throws DaoException {
        DbConnection dbConnection = getDbConnection(dataSource);

        /*switch (modelType) {
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
        }*/
        return null;
    }

    private static <T, ID> IDao<T, ID> createFileDao(ModelType modelType) throws DaoException {
        FileConnection fileConnection = getFileConnection(modelType);
        List<File> fileList = fileConnection.getFileList();

        switch (modelType) {
            case leistung -> {
                Optional<LeistungDaoFile> daoFile = fileList.stream()
                        .filter(file -> file.getType().equals(FileType.csv) || file.getType().equals(FileType.xml))
                        .map(file -> {
                            Path filePath = Path.of(URI.create(file.getValue()));
                            switch (file.getType()) {
                                case FileType.csv -> {
                                    FilePersistenceService<Leistung> filePersistenceService = new FilePersistenceServiceCsv<>(',');
                                    return new LeistungDaoFile(filePersistenceService, filePath);
                                }
                                case FileType.xml -> {
                                    FilePersistenceService<Leistung> filePersistenceService = new FilePersistenceServiceXml<>();
                                    return new LeistungDaoFile(filePersistenceService, filePath);
                                }
                            }
                            return null;
                        })
                        .reduce((leistungDaoFile, leistungDaoFile2) -> {
                            leistungDaoFile2.read().forEach(leistungDaoFile::create);
                            return leistungDaoFile;
                        });
                if (daoFile.isPresent()) {
                    return (AbstractDaoFile<T, ID>) daoFile.get();
                }
                throw new DaoException("Unsupported file type for LEISTUNG: " + modelType);

            }
            case pflegekraft -> {
                Optional<PflegekraftDaoFile> daoFile = fileList.stream()
                        .filter(file -> file.getType().equals(FileType.csv) || file.getType().equals(FileType.xml))
                        .map(file -> {
                            Path filePath = Path.of(URI.create(file.getValue()));
                            switch (file.getType()) {
                                case FileType.csv -> {
                                    FilePersistenceService<Pflegekraft> filePersistenceService = new FilePersistenceServiceCsv<>(',');
                                    return new PflegekraftDaoFile(filePersistenceService, filePath);
                                }
                                case FileType.xml -> {
                                    FilePersistenceService<Pflegekraft> filePersistenceService = new FilePersistenceServiceXml<>();
                                    return new PflegekraftDaoFile(filePersistenceService, filePath);
                                }
                            }
                            return null;
                        })
                        .reduce((leistungDaoFile, leistungDaoFile2) -> {
                            leistungDaoFile2.read().forEach(leistungDaoFile::create);
                            return leistungDaoFile;
                        });
                if (daoFile.isPresent()) {
                    return (AbstractDaoFile<T, ID>) daoFile.get();
                }
                throw new DaoException("Unsupported file type for PFLEGEKRAFT: " + modelType);

            }

            case patient -> {
                Optional<PatientDaoFile> daoFile = fileList.stream()
                        .filter(file -> file.getType().equals(FileType.csv) || file.getType().equals(FileType.xml))
                        .map(file -> {
                            Path filePath = Path.of(URI.create(file.getValue()));
                            switch (file.getType()) {
                                case FileType.csv -> {
                                    FilePersistenceService<Patient> filePersistenceService = new FilePersistenceServiceCsv<>(',');
                                    return new PatientDaoFile(filePersistenceService, filePath);
                                }
                                case FileType.xml -> {
                                    FilePersistenceService<Patient> filePersistenceService = new FilePersistenceServiceXml<>();
                                    return new PatientDaoFile(filePersistenceService, filePath);
                                }
                            }
                            return null;
                        })
                        .reduce((leistungDaoFile, leistungDaoFile2) -> {
                            leistungDaoFile2.read().forEach(leistungDaoFile::create);
                            return leistungDaoFile;
                        });
                if (daoFile.isPresent()) {
                    return (AbstractDaoFile<T, ID>) daoFile.get();
                }
                throw new DaoException("Unsupported file type for PATIENT: " + modelType);

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
