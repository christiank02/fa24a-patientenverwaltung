package patientenverwaltung.configuration.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import patientenverwaltung.configuration.models.enums.ModelType;

public class DataSources {

    private List<DataSource> dataSources;

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public Map<ModelType, DataSource> createDataSourceMap() {
        return dataSources.stream()
                .collect(Collectors.toMap(
                        DataSource::getModel,
                        dataSource -> dataSource
                ));
    }

}
