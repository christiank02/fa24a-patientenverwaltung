package patientenverwaltung.datalayer.services;

import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.services.ConfigurationPersistenceService;
import patientenverwaltung.datalayer.exceptions.DaoException;

public class DataLayerManager {

    private static DataLayerManager instance;
    private IDataLayer dataLayer;

    private DataLayerManager() throws DaoException {
        Configuration configuration = ConfigurationPersistenceService.getInstance().getConfiguration();
        this.dataLayer = DataLayerFactory.createDataLayer(configuration);
    }

    public static DataLayerManager getInstance() throws DaoException {
        if (instance == null) {
            instance = new DataLayerManager();
        }
        return instance;
    }

    public IDataLayer getDataLayer() {
        if (dataLayer == null) {
            throw new IllegalStateException("DataLayer has not been initialized.");
        }
        return dataLayer;
    }
}
