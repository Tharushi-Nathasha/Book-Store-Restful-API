/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.model;

/**
 *
 * @author MSI
 */
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private int customerId;
    private List<CartItem> items = new ArrayList<>();

    // Constructors, getters, and setters
    public Cart() {}

    public Cart(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
