package com.programacion.servicios;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.programacion.clientes.authors.AuthorRestProxy;
import com.programacion.clientes.authors.AuthorsCliente;
import com.programacion.db.Book;
import com.programacion.dtos.BookDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServicioBookImpl implements ServicioBook {

    @Inject
    MongoCollection<Document> collection;

    @RestClient
    @Inject
    AuthorRestProxy proxyAuthor;
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        for (Document document : collection.find()){
            Book book = new Book();
            book.setTitle(document.getString("title"));
            book.setAuthor_id(document.getLong("author_id"));
            book.setPrice(document.getDouble("price"));
            book.setId(document.getObjectId("_id").toHexString());
            book.setIsbn(document.getString("isbn"));
            books.add(book);
        }
        return books;
    }

    @Override
    public Book findById(String id) {
        ObjectId oid =null;
        try {
            oid = new ObjectId(id);
        } catch (Exception e) {

        }
        Document document= collection.find(Filters.eq("_id", oid)).first();
        Book book = new Book();
        book.setTitle(document.getString("title"));
        book.setAuthor_id(document.getLong("author_id"));
        book.setPrice(document.getDouble("price"));
        book.setId(document.getObjectId("_id").toHexString());
        book.setIsbn(document.getString("isbn"));
        return book;
    }

    @Override
    public void insert(Book book) {
        Document document = new Document();
        document.put("title",book.getTitle());
        document.put("isbn",book.getIsbn());
        document.put("price",book.getPrice());
        document.put("author_id",book.getAuthor_id());
        collection.insertOne(document);
    }

    @Override
    public void update(Book book ,String id) {
        ObjectId oid =null;
        try {
            oid = new ObjectId(id);
        } catch (Exception e) {

        }
        Document filterByGradeId = new Document("_id",oid);
        Document document = new Document();
        document.put("title",book.getTitle());
        document.put("isbn",book.getIsbn());
        document.put("price",book.getPrice());
        document.put("author_id",book.getAuthor_id());
        collection.findOneAndReplace(filterByGradeId,document);
    }

    @Override
    public void delete(String id) {
        ObjectId oid =null;
        try {
            oid = new ObjectId(id);
        } catch (Exception e) {

        }
        Document filterByGradeId = new Document(("_id"),oid);
        collection.deleteOne(filterByGradeId);
    }

    @Override
    public List<BookDto> findAllCompleto() {
        List<Book> books = new ArrayList<>();
        for (Document document : collection.find()){
            Book book = new Book();
            book.setTitle(document.getString("title"));
            book.setAuthor_id(document.getLong("author_id"));
            book.setPrice(document.getDouble("price"));
            book.setId(document.getObjectId("_id").toHexString());
            book.setIsbn(document.getString("isbn"));
            books.add(book);
        }
        List<BookDto> ret = books.stream()
                .map(s -> {
                    System.out.println("*********buscando " + s.getAuthor_id() );

                    AuthorsCliente author = proxyAuthor.findById(s.getAuthor_id());
                    return new BookDto(
                            s.getId(),
                            s.getIsbn(),
                            s.getTitle(),
                            s.getAuthor_id(),
                            s.getPrice(),
                            String.format("%s, %s", author.getFirst_name(), author.getLast_name())
                    );
                })
                .collect(Collectors.toList());
        return ret;
    }


}
