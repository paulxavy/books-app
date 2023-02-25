package com.programacion.db;

import jakarta.persistence.MapKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    @BsonProperty(value = "id")
    private String id;
    @BsonProperty(value = "isbn")
    private String isbn;
    @BsonProperty(value = "title")
    private String title;
    @BsonProperty(value = "author_id")
    private Long author_id;
    @BsonProperty(value = "price")
    private Double price;
}
