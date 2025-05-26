package patientenverwaltung.datalayer.services;

import patientenverwaltung.datalayer.dataaccessobjects.IDao;
import patientenverwaltung.models.Leistung;
import patientenverwaltung.models.Patient;
import patientenverwaltung.models.Pflegekraft;

public class DataLayer implements IDataLayer {

    private IDao<Leistung, String> daoLeistung;
    private IDao<Pflegekraft, Long> daoPflegekraft;
    private IDao<Patient, Long> daoPatient;

    @Override
    public IDao<Leistung, String> getDaoLeistung() {
        return daoLeistung;
    }

    @Override
    public IDao<Pflegekraft, Long> getDaoPflegekraft() {
        return daoPflegekraft;
    }

    @Override
    public IDao<Patient, Long> getDaoPatient() {
        return daoPatient;
    }

    protected void setDaoLeistung(IDao<Leistung, String> daoLeistung) {
        this.daoLeistung = daoLeistung;
    }

    protected void setDaoPflegekraft(IDao<Pflegekraft, Long> daoPflegekraft) {
        this.daoPflegekraft = daoPflegekraft;
    }

    protected void setDaoPatient(IDao<Patient, Long> daoPatient) {
        this.daoPatient = daoPatient;
    }
}
