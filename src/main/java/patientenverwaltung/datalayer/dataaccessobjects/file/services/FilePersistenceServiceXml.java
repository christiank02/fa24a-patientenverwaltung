package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import java.nio.file.Path;
import java.util.List;

public class FilePersistenceServiceXml<T> implements FilePersistenceService<T> {

    @Override
    public List<T> readFile(Class<T> classType, Path filePath) {
        return List.of();
    }

    @Override
    public void writeFile(Class<T> classType, List<T> listToPersist, Path filePath) {

    }
}
