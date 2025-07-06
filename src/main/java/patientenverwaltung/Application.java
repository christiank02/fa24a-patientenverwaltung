package patientenverwaltung;

import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.datalayer.services.DataLayerFactory;
import patientenverwaltung.datalayer.services.DataLayerManager;

public class Application {

    public void start() {
        // Initialize the application components
        System.out.println("Starting the Patientenverwaltung application...");

        // Initialize data access layer
        initializeDataAccessLayer();

        // Start the user interface
        startUserInterface();
    }

    private void initializeDataAccessLayer() {
        try {
            DataLayerManager.getInstance().getDataLayer();
        } catch (DaoException e) {
            System.out.println("Error initializing data layer: " + e.getMessage());
        }
    }

    private void startUserInterface() {
        // Logic to start the user interface
        System.out.println("Starting user interface...");
    }

}
