package patientenverwaltung.configuration.models.fileconnection;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import patientenverwaltung.configuration.models.enums.FileType;

@XmlAccessorType(XmlAccessType.FIELD)
public class File {

    @XmlValue
    private String value; // The file full path
    @XmlAttribute
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
