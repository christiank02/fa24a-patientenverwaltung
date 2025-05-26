package patientenverwaltung.datalayer.services;

import javax.sql.DataSource;

import patientenverwaltung.configuration.exceptions.ConfigurationException;
import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.services.ConfigurationPersistenceService;
import patientenverwaltung.datalayer.dataaccessobjects.IDao;

public class DataLayerFactory {

    private static Configuration config;

    public static IDataLayer createDataLayer() {
        if (config == null) {
            try {
                config = ConfigurationPersistenceService.getInstance().getConfiguration();
            } catch (ConfigurationException e) {
                throw new RuntimeException("Failed to load configuration: " + e.getMessage(), e);
            }
        }

        return null;
    }

    private static <T, ID> IDao<T, ID> createDao(Class<T> modelType) {
        // Logic to create and return the appropriate DAO based on the entity class
        // This is a placeholder; actual implementation will depend on the specific DAOs available
        return null; // Replace with actual DAO creation logic
    }

    private static <T, ID> IDao<T, ID> createDBDao(Class<T> modelType, DataSource dataSource) {
        // Logic to create and return the appropriate DAO based on the entity class and type
        // This is a placeholder; actual implementation will depend on the specific DAOs available
        return null; // Replace with actual DAO creation logic
    }

    private static <T, ID> IDao<T, ID> createFileDao(Class<T> modelType, DataSource dataSource) {
        // Logic to create and return the appropriate DAO based on the entity class and type
        // This is a placeholder; actual implementation will depend on the specific DAOs available
        return null; // Replace with actual DAO creation logic
    }

    public static DataSource getDataSource() {
        if (config == null) {
            try {
                config = ConfigurationPersistenceService.getInstance().getConfiguration();
            } catch (ConfigurationException e) {
                throw new RuntimeException("Failed to load configuration: " + e.getMessage(), e);
            }
        }
        return config.getDataSource();
    }



}
