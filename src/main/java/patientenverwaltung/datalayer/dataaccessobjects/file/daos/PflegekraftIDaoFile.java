package patientenverwaltung.datalayer.dataaccessobjects.file.daos;

import java.nio.file.Path;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.models.Pflegekraft;

public class PflegekraftIDaoFile extends AbstractIDaoFile<Pflegekraft, Long> {

    public PflegekraftIDaoFile(FilePersistenceService<Pflegekraft> filePersistenceService, Path filePath) {
        super(filePersistenceService, Pflegekraft.class, filePath);
    }

    @Override
    protected Long getIdFromObject(Pflegekraft object) {
        return object.getId();
    }

    @Override
    protected void setIdToObjectToInsert(Pflegekraft objectToInsert) {
        if (objectToInsert.getId() == 0L) {
            long maxId = cachedObjectList.stream()
                    .mapToLong(Pflegekraft::getId)
                    .max()
                    .orElse(0L);
            objectToInsert.setId(maxId + 1);
        }
    }
}