/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.exception;

/**
 *
 * @author MSI
 */
public class ErrorMessage {
    private String error;
    private String message;

    public ErrorMessage() {}

    public ErrorMessage(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // Getters and setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
