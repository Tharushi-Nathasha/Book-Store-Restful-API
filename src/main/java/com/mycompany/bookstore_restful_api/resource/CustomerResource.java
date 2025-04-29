/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.resource;

/**
 *
 * @author MSI
 */

import com.mycompany.bookstore_restful_api.exception.InvalidInputException;
import com.mycompany.bookstore_restful_api.model.Customer;
import com.mycompany.bookstore_restful_api.service.CustomerService;
import com.mycompany.bookstore_restful_api.exception.CustomerNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerService customerService = new CustomerService();

    @POST
    public Response addCustomer(Customer customer) {
        // Simple validation
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new InvalidInputException("Invalid email address");
        }

        Customer addedCustomer = customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(addedCustomer).build();
    }

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) throws CustomerNotFoundException {
        try {
            Customer customer = customerService.getCustomer(id);
            return Response.ok(customer).build();
        } catch (CustomerNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) throws CustomerNotFoundException {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return Response.ok(updatedCustomer).build();
        } catch (CustomerNotFoundException e) {
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) throws CustomerNotFoundException {
        try {
            customerService.deleteCustomer(id);
            return Response.noContent().build();
        } catch (CustomerNotFoundException e) {
            throw e;
        }
    }
}