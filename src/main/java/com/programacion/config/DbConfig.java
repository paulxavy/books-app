package com.programacion.config;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class DbConfig {

    @Inject
    @ConfigProperty(name = "monguito.dbhost")
    private String dbHost;

    @Inject
    @ConfigProperty(name = "monguito.dbname")
    private String dbName;

    @Inject
    @ConfigProperty(name = "monguito.port")
    private int puerto;

    @Produces
    @ApplicationScoped
    public MongoCollection<Document> mongoDatabase() {
        MongoClient mongo = new MongoClient(dbHost, puerto);
        MongoDatabase database = mongo.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection("book");
        return collection;
    }


}
