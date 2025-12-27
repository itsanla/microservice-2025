package com.anla.cqrs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    
    @Value("${app.services}")
    private String servicesConfig;
    
    @Value("${app.auth.enabled:true}")
    private boolean authEnabled;
    
    private Map<String, String> serviceIpMap;
    
    public boolean isAuthorized(String serviceName, String clientIP) {
        if (!authEnabled) {
            return true;
        }
        
        if (serviceIpMap == null) {
            initializeServiceMap();
        }
        
        String expectedIP = serviceIpMap.get(serviceName);
        return expectedIP != null && expectedIP.equals(clientIP);
    }
    
    private void initializeServiceMap() {
        serviceIpMap = new HashMap<>();
        String[] services = servicesConfig.split(",");
        
        for (String service : services) {
            String[] parts = service.trim().split(":");
            if (parts.length == 3) {
                String name = parts[0];
                String ip = parts[1];
                serviceIpMap.put(name, ip);
            }
        }
    }
    
    public Map<String, String> getServiceIpMap() {
        if (serviceIpMap == null) {
            initializeServiceMap();
        }
        return serviceIpMap;
    }
}