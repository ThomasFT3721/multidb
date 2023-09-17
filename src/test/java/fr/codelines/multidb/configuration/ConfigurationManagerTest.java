package fr.codelines.multidb.configuration;

import org.junit.jupiter.api.Test;

public class ConfigurationManagerTest {

    @Test
    public void testLoadConfiguration() {
        ConfigurationProperties properties = ConfigurationManager.loadConfiguration();
        System.out.println(properties.getDatabaseUrl());
        System.out.println(properties.getDatabaseUsername());
        System.out.println(properties.getDatabasePassword());
    }
}
