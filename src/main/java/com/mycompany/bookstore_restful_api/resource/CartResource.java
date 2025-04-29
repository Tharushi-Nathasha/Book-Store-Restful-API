/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.resource;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Cart;
import com.mycompany.bookstore_restful_api.model.CartItem;
import com.mycompany.bookstore_restful_api.service.CartService;
import com.mycompany.bookstore_restful_api.service.BookService;
import com.mycompany.bookstore_restful_api.service.CustomerService;
import com.mycompany.bookstore_restful_api.exception.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private CartService cartService = new CartService(new BookService());
    private CustomerService customerService = new CustomerService();

    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            Cart cart;
            try {
                cart = cartService.getCart(customerId);
            } catch (CartNotFoundException e) {
                cart = cartService.createCart(customerId);
            }

            cart = cartService.addItemToCart(customerId, item);
            return Response.status(Response.Status.CREATED).entity(cart).build();
        } catch (CustomerNotFoundException | BookNotFoundException | OutOfStockException e) {
            throw e;
        }
    }

    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            Cart cart = cartService.getCart(customerId);
            return Response.ok(cart).build();
        } catch (CustomerNotFoundException | CartNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateCartItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId,
            CartItem item) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            cartService.updateCartItem(customerId, bookId, item.getQuantity());
            Cart cart = cartService.getCart(customerId);
            return Response.ok(cart).build();
        } catch (CustomerNotFoundException | CartNotFoundException |
                 BookNotFoundException | OutOfStockException e) {
            throw e;
        }
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            cartService.removeItemFromCart(customerId, bookId);
            return Response.noContent().build();
        } catch (CustomerNotFoundException | CartNotFoundException | BookNotFoundException e) {
            throw e;
        }
    }
}
