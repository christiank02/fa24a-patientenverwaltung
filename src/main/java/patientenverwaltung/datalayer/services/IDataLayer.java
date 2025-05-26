package patientenverwaltung.datalayer.services;

import patientenverwaltung.datalayer.dataaccessobjects.IDao;
import patientenverwaltung.models.Leistung;
import patientenverwaltung.models.Patient;
import patientenverwaltung.models.Pflegekraft;

public interface IDataLayer {

    IDao<Leistung, String> getDaoLeistung();
    IDao<Pflegekraft, Long> getDaoPflegekraft();
    IDao<Patient, Long> getDaoPatient();
}
