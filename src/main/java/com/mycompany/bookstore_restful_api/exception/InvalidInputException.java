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

public class InvalidInputException extends WebApplicationException {
    public InvalidInputException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiErrorMessage("Invalid Input", message))
                .type("application/json")
                .build());
    }
}
