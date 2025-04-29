/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.resource;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Book;
import com.mycompany.bookstore_restful_api.service.BookService;
import com.mycompany.bookstore_restful_api.exception.BookNotFoundException;
import com.mycompany.bookstore_restful_api.exception.InvalidInputException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookService bookService = new BookService();

    @POST
    public Response addBook(Book book) {
        // Validate input
        if (book.getPublicationYear() > java.time.Year.now().getValue()) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }

        Book addedBook = bookService.addBook(book);
        return Response.status(Response.Status.CREATED).entity(addedBook).build();
    }

    @GET
    public Response getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int id) {
        try {
            Book book = bookService.getBook(id);
            return Response.ok(book).build();
        } catch (BookNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book book) {
        // Validate input
        if (book.getPublicationYear() > java.time.Year.now().getValue()) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }

        try {
            Book updatedBook = bookService.updateBook(id, book);
            return Response.ok(updatedBook).build();
        } catch (BookNotFoundException e) {
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        try {
            bookService.deleteBook(id);
            return Response.noContent().build();
        } catch (BookNotFoundException e) {
            throw e;
        }
    }
}
