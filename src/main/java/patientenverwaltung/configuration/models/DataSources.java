package patientenverwaltung.configuration.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import patientenverwaltung.configuration.models.enums.ModelType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataSources {

    @XmlElement(name = "dataSource")
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
