package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import java.nio.file.Path;
import java.util.List;

public interface FilePersistenceService<T> {

    List<T> readFile(Class<T> classType, Path filePath);
    void writeFile(Class<T> classType, List<T> listToPersist, Path filePath);
}
