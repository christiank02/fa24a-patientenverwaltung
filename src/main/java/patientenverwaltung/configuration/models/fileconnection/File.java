package patientenverwaltung.configuration.models.fileconnection;

import patientenverwaltung.configuration.models.enums.FileType;

public class File {

    private String value;
    private FileType type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }
}
