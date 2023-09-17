package fr.codelines.multidb.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class ConfigurationManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Load configuration from src/main/resources/configurations.json
     * If the file does not exist, it is created with a default configuration
     *
     * @return the configuration
     * @throws RuntimeException if the file cannot be read
     * @see ConfigurationProperties
     */
    public static ConfigurationProperties loadConfiguration() {
        File file = new File("src/main/resources/configurations.json");
        if (!file.exists()) {
            saveConfiguration(ConfigurationProperties.defaultConfiguration());
        }
        try {
            return objectMapper.readValue(file, ConfigurationProperties.class);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load configuration file", e);
        }
    }

    /**
     * Save configuration to src/main/resources/configurations.json
     * If the file does not exist, it is created
     * If the file exists, it is overwritten
     *
     * @param configurationProperties the configuration to save
     * @throws RuntimeException if the file cannot be written
     */
    public static void saveConfiguration(ConfigurationProperties configurationProperties) {
        File file = new File("src/main/resources/configurations.json");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new RuntimeException("Cannot create configuration file");
                }
            } catch (IOException e) {
                throw new RuntimeException("Cannot create configuration file", e);
            }
        }

        try {
            objectMapper.writeValue(file, configurationProperties);
        } catch (Exception e) {
            throw new RuntimeException("Cannot save configuration file", e);
        }
    }
}
