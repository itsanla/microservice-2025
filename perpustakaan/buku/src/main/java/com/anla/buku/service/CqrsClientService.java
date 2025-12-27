package com.anla.buku.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * CQRS client service for database operations.
 */
@Service
public final class CqrsClientService {
    
    /** CQRS service URL. */
    @Value("${cqrs.service.url}")
    private String cqrsUrl;
    
    /** REST template for HTTP calls. */
    private final RestTemplate restTemplate;
    
    /** Data key constant. */
    private static final String DATA_KEY = "data";
    
    /**
     * Constructor.
     */
    public CqrsClientService() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Save entity to CQRS.
     * @param data entity data
     * @param entityId entity identifier
     */
    public void save(final Object data, final String entityId) {
        final Map<String, Object> command = new ConcurrentHashMap<>();
        command.put("id", entityId);
        command.put("eventType", "CREATE");
        command.put(DATA_KEY, data);
        
        restTemplate.postForObject(cqrsUrl + "/api/cqrs/buku/command", command, String.class);
    }
    
    /**
     * Update entity in CQRS.
     * @param data entity data
     * @param entityId entity identifier
     */
    public void update(final Object data, final String entityId) {
        final Map<String, Object> command = new ConcurrentHashMap<>();
        command.put("id", entityId);
        command.put("eventType", "UPDATE");
        command.put(DATA_KEY, data);
        
        restTemplate.postForObject(cqrsUrl + "/api/cqrs/buku/command", command, String.class);
    }
    
    /**
     * Delete entity from CQRS.
     * @param entityId entity identifier
     */
    public void delete(final String entityId) {
        final Map<String, Object> command = new ConcurrentHashMap<>();
        command.put("id", entityId);
        command.put("eventType", "DELETE");
        command.put(DATA_KEY, Map.of("deleted", true));
        
        restTemplate.postForObject(cqrsUrl + "/api/cqrs/buku/command", command, String.class);
    }
    
    /**
     * Find entity by ID.
     * @param entityId entity identifier
     * @return entity data or null
     */
    @SuppressWarnings("unchecked")
    public Object findById(final String entityId) {
        Object result;
        try {
            final Map<String, Object> response = restTemplate.getForObject(
                cqrsUrl + "/api/cqrs/buku/query/" + entityId, Map.class);
            result = response != null ? response.get(DATA_KEY) : null;
        } catch (org.springframework.web.client.RestClientException exception) {
            result = null;
        }
        return result;
    }
    
    /**
     * Find all entities.
     * @return list of entities
     */
    @SuppressWarnings("unchecked")
    public List<Object> findAll() {
        List<Object> result;
        try {
            result = restTemplate.getForObject(cqrsUrl + "/api/cqrs/buku/query", List.class);
            if (result == null) {
                result = List.of();
            }
        } catch (org.springframework.web.client.RestClientException exception) {
            result = List.of();
        }
        return result;
    }
}