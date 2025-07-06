package patientenverwaltung.configuration.models.fileconnection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import patientenverwaltung.configuration.models.enums.ModelType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileConnections {

    @XmlElement(name = "fileConnection")
    private List<FileConnection> fileConnectionList;

    public List<FileConnection> getFileConnectionList() {
        return fileConnectionList;
    }

    public Map<ModelType, FileConnection> createFileConnectionMap() {
        return fileConnectionList.stream()
                .collect(Collectors.toMap(FileConnection::getModel, fileConnection -> fileConnection));
    }

}
