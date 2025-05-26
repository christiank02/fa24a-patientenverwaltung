package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import java.nio.file.Path;
import java.util.List;

public class FilePersistenceServiceCsv<T> implements FilePersistenceService<T> {

    private char separator;

    public FilePersistenceServiceCsv(char separator) {
        this.separator = separator;
    }

    @Override
    public List<T> readFile(Class<T> classType, Path filePath) {
        return List.of();
    }

    @Override
    public void writeFile(Class<T> classType, List<T> listToPersist, Path filePath) {

    }

    private String[] getCsvColumnNames(Class<T> classType) {
        return new String[]{"id", "name", "description"};
    }
}
