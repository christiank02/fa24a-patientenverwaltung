package patientenverwaltung;

import patientenverwaltung.datalayer.dataaccessobjects.file.daos.LeistungDaoFile;
import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.datalayer.services.DataLayer;
import patientenverwaltung.datalayer.services.DataLayerFactory;
import patientenverwaltung.datalayer.services.DataLayerManager;
import patientenverwaltung.datalayer.services.IDataLayer;
import patientenverwaltung.models.Leistung;

import java.util.List;

public class Application {

    public void start() {
        // Initialize the application components
        try {
            IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();
            // Start the user interface
            startUserInterface(dataLayer);
        } catch (DaoException e) {
            System.out.println("Error initializing data layer: " + e.getMessage());
        }
    }

    private void startUserInterface(IDataLayer dataLayer) throws DaoException {
        // Logic to start the user interface
        System.out.println("Starting user interface...");
        List<Leistung> leistungList = dataLayer.getDaoLeistung().read();
        Leistung leistung = leistungList.getFirst();
        System.out.println("Leistung: " + leistung.getLkNr() + ", Bezeichnung: " + leistung.getBezeichnung() + ", Beschreibung: " +  leistung.getBeschreibung());
        leistung.setBeschreibung(leistung.getBeschreibung() + " - Test");
        dataLayer.getDaoLeistung().update(leistung);
        Leistung updatedLeistung = dataLayer.getDaoLeistung().read(leistung.getLkNr());
        System.out.println("Leistung: " + leistung.getLkNr() + ", Bezeichnung: " + leistung.getBezeichnung() + ", Beschreibung: " +  leistung.getBeschreibung());
    }

}
