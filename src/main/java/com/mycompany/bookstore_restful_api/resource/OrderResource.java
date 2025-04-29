/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.resource;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Order;
import com.mycompany.bookstore_restful_api.service.OrderService;
import com.mycompany.bookstore_restful_api.service.BookService;
import com.mycompany.bookstore_restful_api.service.CartService;
import com.mycompany.bookstore_restful_api.service.CustomerService;
import com.mycompany.bookstore_restful_api.exception.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderService orderService = new OrderService(new BookService(), new CartService(new BookService()));
    private CustomerService customerService = new CustomerService();

    @POST
    public Response createOrder(@PathParam("customerId") int customerId) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            Order order = orderService.createOrder(customerId);
            return Response.status(Response.Status.CREATED).entity(order).build();
        } catch (CustomerNotFoundException | CartNotFoundException |
                 BookNotFoundException | OutOfStockException e) {
            throw e;
        }
    }

    @GET
    public Response getCustomerOrders(@PathParam("customerId") int customerId) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            List<Order> orders = orderService.getCustomerOrders(customerId);
            return Response.ok(orders).build();
        } catch (CustomerNotFoundException e) {
            throw e;
        }
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(
            @PathParam("customerId") int customerId,
            @PathParam("orderId") int orderId) {
        try {
            // Verify customer exists
            customerService.getCustomer(customerId);

            Order order = orderService.getOrder(customerId, orderId);
            if (order == null) {
                throw new NotFoundException("Order not found for this customer.");
            }
            return Response.ok(order).build();
        } catch (CustomerNotFoundException e) {
            throw e;
        }
    }
    
    
}
