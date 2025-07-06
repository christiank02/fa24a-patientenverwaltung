package patientenverwaltung.datalayer.dataaccessobjects.file.daos;

import java.nio.file.Path;

import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.models.Leistung;

public class LeistungDaoFile extends AbstractDaoFile<Leistung, String> {

    public LeistungDaoFile(FilePersistenceService<Leistung> filePersistenceService, Path filePath) {
        super(filePersistenceService, Leistung.class, filePath);
    }

    @Override
    protected String getIdFromObject(Leistung object) {
        return object.getLkNr();
    }

    @Override
    protected void setIdToObjectToInsert(Leistung objectToInsert) {
        objectToInsert.setLkNr(getIdFromObject(objectToInsert));
    }
}