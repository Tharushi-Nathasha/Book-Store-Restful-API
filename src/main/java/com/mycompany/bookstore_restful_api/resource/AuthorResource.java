/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.resource;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Author;
import com.mycompany.bookstore_restful_api.model.Book;
import com.mycompany.bookstore_restful_api.service.AuthorService;
import com.mycompany.bookstore_restful_api.service.BookService;
import com.mycompany.bookstore_restful_api.exception.AuthorNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private AuthorService authorService = new AuthorService();
    private BookService bookService = new BookService();

    @POST
    public Response addAuthor(Author author) {
        Author addedAuthor = authorService.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(addedAuthor).build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) throws AuthorNotFoundException {
        try {
            Author author = authorService.getAuthor(id);
            return Response.ok(author).build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) throws AuthorNotFoundException {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, author);
            return Response.ok(updatedAuthor).build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) throws AuthorNotFoundException {
        try {
            authorService.deleteAuthor(id);
            return Response.noContent().build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int authorId) throws AuthorNotFoundException {
        try {
            // Verify author exists
            authorService.getAuthor(authorId);

            List<Book> books = bookService.getAllBooks().stream()
                    .filter(book -> book.getAuthorId() == authorId)
                    .collect(Collectors.toList());

            return Response.ok(books).build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }
}
