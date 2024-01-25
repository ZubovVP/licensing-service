package ru.zubov.licensingservice.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesScan
@Component
public class ServiceConfig {
    private String property;

    public String getProperty() {
        return property;
    }
}
