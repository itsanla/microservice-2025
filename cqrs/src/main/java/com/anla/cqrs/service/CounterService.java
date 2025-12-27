package com.anla.cqrs.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    
    @Autowired
    private MongoClient mongoClient;
    
    public long getNextSequence(String serviceName) {
        MongoDatabase database = mongoClient.getDatabase("cqrs_" + serviceName);
        MongoCollection<Document> counters = database.getCollection("counters");
        
        Document query = new Document("_id", "book_id");
        Document update = new Document("$inc", new Document("seq", 1));
        Document options = new Document("returnDocument", com.mongodb.client.model.ReturnDocument.AFTER)
            .append("upsert", true);
        
        Document result = counters.findOneAndUpdate(query, update, 
            new com.mongodb.client.model.FindOneAndUpdateOptions()
                .returnDocument(com.mongodb.client.model.ReturnDocument.AFTER)
                .upsert(true));
        
        if (result == null) {
            counters.insertOne(new Document("_id", "book_id").append("seq", 1L));
            return 1L;
        }
        
        return result.getLong("seq");
    }
}
