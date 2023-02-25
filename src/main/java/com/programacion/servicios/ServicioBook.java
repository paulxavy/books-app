package com.programacion.servicios;

import com.programacion.db.Book;
import com.programacion.dtos.BookDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ServicioBook {
    List<Book> findAll( );
    List<BookDto> findAllCompleto( );

    Book findById(String id);

    void insert(Book book);

    void update( Book book ,String id);

    void delete(String id);
}
