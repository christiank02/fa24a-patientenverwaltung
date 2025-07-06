package patientenverwaltung.configuration.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import patientenverwaltung.configuration.models.enums.ConnectionType;
import patientenverwaltung.configuration.models.enums.ModelType;
import patientenverwaltung.configuration.models.enums.SourceType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataSource {

    @XmlAttribute
    private ModelType model;

    @XmlAttribute
    private SourceType source;

    @XmlAttribute
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
