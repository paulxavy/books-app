package com.programacion.rest;

import com.programacion.db.Book;
import com.programacion.dtos.BookDto;
import com.programacion.servicios.ServicioBook;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private ServicioBook servicioBook;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){
        return servicioBook.findAll();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookDto> findAllCompleto(){
        return servicioBook.findAllCompleto();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") String id){
        return servicioBook.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearBook(Book singer) {
        servicioBook.insert(singer);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrarBook(@PathParam("id") String id) {
        servicioBook.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(Book book ,@PathParam("id") String id) {
        servicioBook.update(book,id);
        return Response.status(Response.Status.OK).build();
    }
}
