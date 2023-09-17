package fr.codelines.multidb.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigurationProperties {

    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    ConfigurationProperties() {
    }

    public static ConfigurationProperties defaultConfiguration() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties();
        configurationProperties.setDatabaseUrl("jdbc:h2:mem:default");
        configurationProperties.setDatabaseUsername("sa");
        configurationProperties.setDatabasePassword("");
        return configurationProperties;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }
}
