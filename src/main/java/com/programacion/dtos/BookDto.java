package com.programacion.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Access(AccessType.FIELD)

public class BookDto implements Serializable {
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

    private String authorName;
}
