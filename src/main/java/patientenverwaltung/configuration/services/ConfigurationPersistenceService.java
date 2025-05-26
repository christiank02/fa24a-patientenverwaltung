package patientenverwaltung.configuration.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import patientenverwaltung.configuration.exceptions.ConfigurationException;
import patientenverwaltung.configuration.models.Configuration;

public class ConfigurationPersistenceService {

    private static ConfigurationPersistenceService instance;
    private final String filePath = "config.ser";
    private Configuration configuration;

    private ConfigurationPersistenceService() throws ConfigurationException {
        this.configuration = readFile();
    }

    public static ConfigurationPersistenceService getInstance() throws ConfigurationException {
        if (instance == null) {
            instance = new ConfigurationPersistenceService();
        }
        return instance;
    }

    public Configuration getConfiguration() throws ConfigurationException {
        if (configuration == null) {
            configuration = readFile();
        }
        return configuration;
    }

    public Configuration readFile() throws ConfigurationException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new Configuration();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Configuration) ois.readObject();
        } catch (Exception e) {
            throw new ConfigurationException("Error reading configuration file: " + e.getMessage());
        }
    }

    public void writeFile(Configuration configuration) throws ConfigurationException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(configuration);
            this.configuration = configuration;
        } catch (IOException e) {
            throw new ConfigurationException("Error writing configuration file: " + e.getMessage());
        }
    }
}