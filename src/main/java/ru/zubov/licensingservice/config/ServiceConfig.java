package ru.zubov.licensingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

//@ConfigurationProperties(prefix = "example")
@ConfigurationPropertiesScan
public class ServiceConfig{
    private String property;
    public String getProperty(){
        return property;
    }
}
