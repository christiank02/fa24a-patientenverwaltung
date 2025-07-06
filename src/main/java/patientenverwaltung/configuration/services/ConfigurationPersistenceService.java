package patientenverwaltung.configuration.services;

import com.sun.istack.Nullable;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import patientenverwaltung.configuration.models.Configuration;

import java.io.File;

/**
 * Service for managing the persistence of configuration data.
 */
public class ConfigurationPersistenceService {

    private static ConfigurationPersistenceService instance;

    private String filePath = "src/main/resources/files/appconfig.xml"; // The path to the configuration file
    private Configuration configuration;

    private ConfigurationPersistenceService() {
        Configuration config = readFile();
        if (config == null) {
            System.out.println("Configuration file not found or invalid. Creating a new configuration.");
            configuration = new Configuration();
            writeFile(configuration);
        } else {
            System.out.println("Configuration file loaded successfully.");
            configuration = config;
        }
    }

    public static ConfigurationPersistenceService getInstance() {
        if (instance == null) {
            instance = new ConfigurationPersistenceService();
        }
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Nullable
    private Configuration readFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Configuration) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            System.out.println("Error reading configuration file: " + e.getMessage());
            return null;
        }
    }

    private void writeFile(Configuration configuration) {
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(configuration, new File(filePath));
        } catch (JAXBException e) {
            System.out.println("Error writing configuration file: " + e.getMessage());
        }
    }
}