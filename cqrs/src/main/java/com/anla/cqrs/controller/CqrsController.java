package com.anla.cqrs.controller;

import com.anla.cqrs.event.Event;
import com.anla.cqrs.model.ReadModel;
import com.anla.cqrs.service.EventStoreService;
import com.anla.cqrs.service.ReadModelService;
import com.anla.cqrs.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cqrs")
public class CqrsController {
    
    @Autowired
    private EventStoreService eventStoreService;
    
    @Autowired
    private ReadModelService readModelService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private AuthService authService;
    
    // Command Side - Store Events
    @PostMapping("/{serviceName}/command")
    public ResponseEntity<String> handleCommand(
            @PathVariable String serviceName,
            @RequestBody Map<String, Object> command,
            HttpServletRequest request) {
        
        String clientIP = getClientIP(request);
        
        if (!authService.isAuthorized(serviceName, clientIP)) {
            return ResponseEntity.status(403).body("Unauthorized access from IP: " + clientIP);
        }
        
        try {
            Event event = new Event();
            event.setServiceName(serviceName);
            event.setAggregateId(command.get("id").toString());
            event.setEventType(command.get("eventType").toString());
            event.setEventData(objectMapper.writeValueAsString(command.get("data")));
            event.setTimestamp(LocalDateTime.now());
            event.setVersion(1L);
            
            eventStoreService.saveEvent(event);
            
            // Update Read Model
            updateReadModel(serviceName, event);
            
            return ResponseEntity.ok("Command processed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing command: " + e.getMessage());
        }
    }
    
    // Query Side - Get Read Models
    @GetMapping("/{serviceName}/query")
    public ResponseEntity<List<ReadModel>> getAllByService(
            @PathVariable String serviceName,
            HttpServletRequest request) {
        
        String clientIP = getClientIP(request);
        
        if (!authService.isAuthorized(serviceName, clientIP)) {
            return ResponseEntity.status(403).build();
        }
        
        List<ReadModel> readModels = readModelService.findByServiceName(serviceName);
        return ResponseEntity.ok(readModels);
    }
    
    @GetMapping("/{serviceName}/query/{id}")
    public ResponseEntity<ReadModel> getById(
            @PathVariable String serviceName,
            @PathVariable String id,
            HttpServletRequest request) {
        
        String clientIP = getClientIP(request);
        
        if (!authService.isAuthorized(serviceName, clientIP)) {
            return ResponseEntity.status(403).build();
        }
        
        ReadModel readModel = readModelService.findByAggregateId(serviceName, id);
        if (readModel != null) {
            return ResponseEntity.ok(readModel);
        }
        return ResponseEntity.notFound().build();
    }
    
    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xServiceIP = request.getHeader("X-Service-IP");
        if (xServiceIP != null && !xServiceIP.isEmpty()) {
            return xServiceIP;
        }
        
        return request.getRemoteAddr();
    }
    
    // Event Store Access
    @GetMapping("/{serviceName}/events")
    public ResponseEntity<List<Map<String, Object>>> getEventsByService(@PathVariable String serviceName) {
        List<Map<String, Object>> events = eventStoreService.findEventsByService(serviceName);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/{serviceName}/events/{aggregateId}")
    public ResponseEntity<List<Map<String, Object>>> getEventsByAggregate(
            @PathVariable String serviceName,
            @PathVariable String aggregateId) {
        
        List<Map<String, Object>> events = eventStoreService.findEventsByAggregate(serviceName, aggregateId);
        return ResponseEntity.ok(events);
    }
    
    private void updateReadModel(String serviceName, Event event) {
        try {
            ReadModel readModel = new ReadModel();
            readModel.setServiceName(serviceName);
            readModel.setAggregateId(event.getAggregateId());
            
            Map<String, Object> eventData = objectMapper.readValue(event.getEventData(), Map.class);
            readModel.setData(eventData);
            readModel.setLastUpdated(LocalDateTime.now());
            
            readModelService.saveReadModel(readModel);
        } catch (Exception e) {
            System.err.println("Error updating read model: " + e.getMessage());
        }
    }
}