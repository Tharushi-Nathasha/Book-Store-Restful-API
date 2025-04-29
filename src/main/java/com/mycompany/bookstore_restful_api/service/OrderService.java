/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.service;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.*;
import com.mycompany.bookstore_restful_api.exception.CartNotFoundException;
import com.mycompany.bookstore_restful_api.exception.BookNotFoundException;
import com.mycompany.bookstore_restful_api.exception.OutOfStockException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static Map<Integer, Order> orders = new HashMap<>();
    private static int nextId = 1;
    private BookService bookService;
    private CartService cartService;

    public OrderService(BookService bookService, CartService cartService) {
        this.bookService = bookService;
        this.cartService = cartService;
    }

    public List<Order> getCustomerOrders(int customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    public Order getOrder(int customerId, int orderId) {
        Order order = orders.get(orderId);
        if (order != null && order.getCustomerId() == customerId) {
            return order;
        }
        return null;
    }

    public Order createOrder(int customerId)
            throws CartNotFoundException, BookNotFoundException, OutOfStockException {
        Cart cart = cartService.getCart(customerId);

        // Check stock availability
        for (CartItem item : cart.getItems()) {
            Book book = bookService.getBook(item.getBookId());
            if (book.getStock() < item.getQuantity()) {
                throw new OutOfStockException("Not enough stock for book with ID " + item.getBookId());
            }
        }

        // Create order
        Order order = new Order();
        order.setId(nextId++);
        order.setCustomerId(customerId);
        order.setOrderDate(new Date());

        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Book book = bookService.getBook(cartItem.getBookId());

            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(book.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(book.getPrice());

            orderItems.add(orderItem);
            totalAmount += book.getPrice() * cartItem.getQuantity();

            // Update book stock
            book.setStock(book.getStock() - cartItem.getQuantity());
            bookService.updateBook(book.getId(), book);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        orders.put(order.getId(), order);

        // Clear the cart
        cart.getItems().clear();

        return order;
        
        
    }  
}
