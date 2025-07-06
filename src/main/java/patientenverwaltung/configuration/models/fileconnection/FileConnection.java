package patientenverwaltung.configuration.models.fileconnection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import patientenverwaltung.configuration.models.enums.FileType;
import patientenverwaltung.configuration.models.enums.ModelType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileConnection {

    @XmlAttribute
    private ModelType model;
    @XmlElement(name = "file")
    private List<File> fileList;

    public ModelType getModel() {
        return model;
    }

    public void setModel(ModelType model) {
        this.model = model;
    }

    public List<File> getFileList() {
        return fileList;
    }
}
