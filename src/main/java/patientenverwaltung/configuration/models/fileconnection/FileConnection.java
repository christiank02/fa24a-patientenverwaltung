package patientenverwaltung.configuration.models.fileconnection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import patientenverwaltung.configuration.models.enums.FileType;
import patientenverwaltung.configuration.models.enums.ModelType;

public class FileConnection {

    private ModelType model;
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
