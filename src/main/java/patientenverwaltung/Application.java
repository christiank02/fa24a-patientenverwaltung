package patientenverwaltung;

import patientenverwaltung.datalayer.dataaccessobjects.file.daos.LeistungDaoFile;
import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.datalayer.services.DataLayer;
import patientenverwaltung.datalayer.services.DataLayerFactory;
import patientenverwaltung.datalayer.services.DataLayerManager;
import patientenverwaltung.datalayer.services.IDataLayer;
import patientenverwaltung.models.Leistung;
import patientenverwaltung.models.Patient;
import patientenverwaltung.models.Pflegekraft;

import java.util.List;

public class Application {

    public void start() {
        try {
            IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();
            startUserInterface(dataLayer);
        } catch (DaoException e) {
            System.out.println("Error initializing data layer: " + e.getMessage());
        }
    }

    private void startUserInterface(IDataLayer dataLayer) throws DaoException {
        // Logic to start the user interface
        System.out.println("Starting user interface...");
        printLeistungData();
        printPflegekraftData();
        printPatientData();
    }

    private void printLeistungData() throws DaoException {
        System.out.println("Printing Leistung data...");
        IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();
        List<Leistung> leistungList = dataLayer.getDaoLeistung().read();
        for (Leistung leistung : leistungList) {
            System.out.println("Leistung: " + leistung.getLkNr() + ", Bezeichnung: " + leistung.getBezeichnung() + ", Beschreibung: " +  leistung.getBeschreibung());
        }

        // Example of updating a Leistung
        if (!leistungList.isEmpty()) {
            Leistung leistung = leistungList.getFirst();
            leistung.setBeschreibung(leistung.getBeschreibung() + " - Updated");
            dataLayer.getDaoLeistung().update(leistung);
            Leistung updatedLeistung = dataLayer.getDaoLeistung().read(leistung.getLkNr());
            System.out.println("Updated Leistung: " + updatedLeistung.getLkNr() + ", Bezeichnung: " + updatedLeistung.getBezeichnung() + ", Beschreibung: " +  updatedLeistung.getBeschreibung());
        }
    }

    private void printPflegekraftData() throws DaoException {
        System.out.println("Printing Pflegekraft data...");
        IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();
        List<Pflegekraft> pflegekraftList = dataLayer.getDaoPflegekraft().read();
        for (Pflegekraft pflegekraft : pflegekraftList) {
            System.out.println("Pflegekraft: " + pflegekraft.getId() + ", Name: " + pflegekraft.getNachname() + ", " + pflegekraft.getVorname() + ", Geburtsdatum: " + pflegekraft.getTelefon());
        }

        // Example of updating a Pflegekraft
        if (!pflegekraftList.isEmpty()) {
            Pflegekraft pflegekraft = pflegekraftList.getFirst();
            pflegekraft.setNachname(pflegekraft.getNachname() + " - Updated");
            dataLayer.getDaoPflegekraft().update(pflegekraft);
            Pflegekraft updatedPflegekraft = dataLayer.getDaoPflegekraft().read(pflegekraft.getId());
            System.out.println("Updated Pflegekraft: " + updatedPflegekraft.getId() + ", Name: " + updatedPflegekraft.getNachname() + ", " + updatedPflegekraft.getVorname() + ", Geburtsdatum: " + updatedPflegekraft.getTelefon());
        }
    }

    private void printPatientData() throws DaoException {
        System.out.println("Printing Patient data...");
        IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();
        List<Patient> patientList = dataLayer.getDaoPatient().read();
        for (Patient patient : patientList) {
            System.out.println("Patient: " + patient.getId() + ", Name: " + patient.getNachname() + ", " + patient.getVorname() + ", Geburtsdatum: " + patient.getGeburtsdatum() + ", Zimmer: " + patient.getZimmer() + ", Pflegegrad: " + patient.getPflegegrad() + ", Vermögen: " + patient.getVermoegen());
        }

        // Example of updating a Pflegekraft
        if (!patientList.isEmpty()) {
            Patient patient = patientList.getFirst();
            patient.setNachname(patient.getNachname() + " - Updated");
            dataLayer.getDaoPatient().update(patient);
            Patient updatedPatient = dataLayer.getDaoPatient().read(patient.getId());
            System.out.println("Updated Patient: " + updatedPatient.getId() + ", Name: " + updatedPatient.getNachname() + ", " + updatedPatient.getVorname() + ", Geburtsdatum: " + updatedPatient.getGeburtsdatum() + ", Zimmer: " + updatedPatient.getZimmer() + ", Pflegegrad: " + updatedPatient.getPflegegrad() + ", Vermögen: " + updatedPatient.getVermoegen());
        }
    }

}
