package com.anla.cqrs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MultiDatabaseConfig {
    
    @Value("${app.services}")
    private String servicesConfig;
    
    private final Map<String, DataSource> h2DataSources = new ConcurrentHashMap<>();
    
    public DataSource getH2DataSource(String serviceName) {
        return h2DataSources.computeIfAbsent(serviceName, this::createH2DataSource);
    }
    
    private DataSource createH2DataSource(String serviceName) {
        return DataSourceBuilder.create()
            .driverClassName("org.h2.Driver")
            .url("jdbc:h2:file:./h2db/" + serviceName + "_eventstore")
            .username("sa")
            .password("password")
            .build();
    }
    
    public String getMongoDatabase(String serviceName) {
        return "cqrs_" + serviceName;
    }
}
