/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.exception;

/**
 *
 * @author MSI
 */
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CustomerNotFoundException extends WebApplicationException {
    public CustomerNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new ApiErrorMessage("Customer Not Found", message))
                .type("application/json")
                .build());
    }
}
