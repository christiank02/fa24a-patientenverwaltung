package patientenverwaltung.configuration.models;

import patientenverwaltung.configuration.models.enums.ConnectionType;
import patientenverwaltung.configuration.models.enums.ModelType;
import patientenverwaltung.configuration.models.enums.SourceType;

public class DataSource {

    private ModelType model;
    private SourceType source;
    private ConnectionType type;

    public ModelType getModel() {
        return model;
    }

    public void setModel(ModelType model) {
        this.model = model;
    }

    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public ConnectionType getType() {
        return type;
    }

    public void setType(ConnectionType type) {
        this.type = type;
    }
}
