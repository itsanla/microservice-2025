package com.anla.cqrs.service;

import com.anla.cqrs.config.MultiDatabaseConfig;
import com.anla.cqrs.model.ReadModel;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReadModelService {
    
    @Autowired
    private MongoClient mongoClient;
    
    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;
    
    private MongoCollection<Document> getCollection(String serviceName) {
        String dbName = multiDatabaseConfig.getMongoDatabase(serviceName);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        return database.getCollection("read_models");
    }
    
    public void saveReadModel(ReadModel readModel) {
        MongoCollection<Document> collection = getCollection(readModel.getServiceName());
        
        Document query = new Document("aggregateId", readModel.getAggregateId());
        Document existing = collection.find(query).first();
        
        Document doc = new Document()
            .append("aggregateId", readModel.getAggregateId())
            .append("data", readModel.getData())
            .append("lastUpdated", readModel.getLastUpdated().toString());
        
        if (existing != null) {
            collection.replaceOne(query, doc);
        } else {
            collection.insertOne(doc);
        }
    }
    
    public ReadModel findByAggregateId(String serviceName, String aggregateId) {
        MongoCollection<Document> collection = getCollection(serviceName);
        Document query = new Document("aggregateId", aggregateId);
        Document doc = collection.find(query).first();
        
        if (doc == null) return null;
        
        ReadModel model = new ReadModel();
        model.setId(doc.getObjectId("_id").toString());
        model.setServiceName(serviceName);
        model.setAggregateId(doc.getString("aggregateId"));
        model.setData((Map<String, Object>) doc.get("data"));
        model.setLastUpdated(LocalDateTime.parse(doc.getString("lastUpdated")));
        
        return model;
    }
    
    public List<ReadModel> findByServiceName(String serviceName) {
        MongoCollection<Document> collection = getCollection(serviceName);
        List<ReadModel> models = new ArrayList<>();
        
        for (Document doc : collection.find()) {
            ReadModel model = new ReadModel();
            model.setId(doc.getObjectId("_id").toString());
            model.setServiceName(serviceName);
            model.setAggregateId(doc.getString("aggregateId"));
            model.setData((Map<String, Object>) doc.get("data"));
            model.setLastUpdated(LocalDateTime.parse(doc.getString("lastUpdated")));
            models.add(model);
        }
        
        return models;
    }
}
