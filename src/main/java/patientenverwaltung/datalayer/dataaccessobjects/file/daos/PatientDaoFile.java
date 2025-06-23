package patientenverwaltung.datalayer.dataaccessobjects.file.daos;

import java.nio.file.Path;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.models.Patient;

public class PatientDaoFile extends AbstractDaoFile<Patient, Long> {

    public PatientDaoFile(FilePersistenceService<Patient> filePersistenceService, Path filePath) {
        super(filePersistenceService, Patient.class, filePath);
    }

    @Override
    protected Long getIdFromObject(Patient object) {
        return object.getId();
    }

    @Override
    protected void setIdToObjectToInsert(Patient objectToInsert) {
        // Beispiel: Setze neue ID, falls noch nicht gesetzt (Autoincrement)
        if (objectToInsert.getId() == 0L) {
            long maxId = cachedObjectList.stream()
                    .mapToLong(Patient::getId)
                    .max()
                    .orElse(0L);
            objectToInsert.setId(maxId + 1);
        }
    }
}