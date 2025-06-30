package patientenverwaltung.configuration.models.fileconnection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import patientenverwaltung.configuration.models.enums.ModelType;

public class FileConnections {

    private List<FileConnection> fileConnectionList;

    public List<FileConnection> getFileConnectionList() {
        return fileConnectionList;
    }

    public Map<ModelType, FileConnection> createFileConnectionMap() {
        return fileConnectionList.stream()
                .collect(Collectors.toMap(FileConnection::getModel, fileConnection -> fileConnection));
    }

}
