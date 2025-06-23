package patientenverwaltung.configuration.models.enums;

public enum FileType {
    XML("xml"),
    CSV("csv");

    private final String fileExtension;

    FileType(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public String toString() {
        return fileExtension;
    }
}
