package patientenverwaltung.datalayer.services;

import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.models.DataSource;
import patientenverwaltung.configuration.models.DataSources;
import patientenverwaltung.configuration.models.enums.ModelType;
import patientenverwaltung.datalayer.dataaccessobjects.IDao;

public class DataLayerFactory {

    private static Configuration config;

    public static IDataLayer createDataLayer(Configuration configuration) {
        config = configuration;

        DataLayer dataLayer = new DataLayer();
        dataLayer.setDaoLeistung(createDao(ModelType.LEISTUNG));
        dataLayer.setDaoPflegekraft(createDao(ModelType.PFLEGEKRAFT));
        dataLayer.setDaoPatient(createDao(ModelType.PATIENT));

        return dataLayer;
    }

    private static <T, ID> IDao<T, ID> createDao(ModelType modelType) {
        DataSources dataSources = config.getDataSources();
        DataSource dataSource = dataSources.createDataSourceMap().get(modelType);

        if (dataSource == null) {
            throw new IllegalArgumentException("No DataSource found for model type: " + modelType);
        }

        return switch (dataSource.getSource()) {
            case DB -> createDbDao(modelType, dataSource);
            case FILE -> createFileDao(modelType, dataSource);
        };
    }

    private static <T, ID> IDao<T, ID> createDbDao(ModelType modelType, DataSource dataSource) {

    }

    private static <T, ID> IDao<T, ID> createFileDao(ModelType modelType, DataSource dataSource) {

    }


}
