package patientenverwaltung;

import patientenverwaltung.configuration.models.Configuration;
import patientenverwaltung.configuration.services.ConfigurationPersistenceService;

public class Application {

    public void start() {
        // Initialize the application components
        System.out.println("Starting the Patientenverwaltung application...");

        // Load configuration
        Configuration configuration = loadConfiguration();

        // Initialize data access layer
        initializeDataAccessLayer();

        // Start the user interface
        startUserInterface();
    }

    private Configuration loadConfiguration() {
        return ConfigurationPersistenceService.getInstance().getConfiguration();
    }

    private void initializeDataAccessLayer() {
        // Logic to initialize data access layer
        System.out.println("Initializing data access layer...");
    }

    private void startUserInterface() {
        // Logic to start the user interface
        System.out.println("Starting user interface...");
    }

}
