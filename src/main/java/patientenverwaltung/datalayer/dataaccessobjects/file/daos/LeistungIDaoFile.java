package patientenverwaltung.datalayer.dataaccessobjects.file.daos;

import java.nio.file.Path;

import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.models.Leistung;

public class LeistungIDaoFile extends AbstractIDaoFile<Leistung, String> {

    public LeistungIDaoFile(FilePersistenceService<Leistung> filePersistenceService, Path filePath) {
        super(filePersistenceService, Leistung.class, filePath);
    }

    @Override
    protected String getIdFromObject(Leistung object) {
        return object.getLkNr();
    }

    @Override
    protected void setIdToObjectToInsert(Leistung objectToInsert) {
        // Leistung objects already have their ID set, so no action needed here.
    }
}