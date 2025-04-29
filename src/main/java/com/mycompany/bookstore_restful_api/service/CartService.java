/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.service;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Book;
import com.mycompany.bookstore_restful_api.model.Cart;
import com.mycompany.bookstore_restful_api.model.CartItem;
import com.mycompany.bookstore_restful_api.exception.CartNotFoundException;
import com.mycompany.bookstore_restful_api.exception.BookNotFoundException;
import com.mycompany.bookstore_restful_api.exception.OutOfStockException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    private static Map<Integer, Cart> carts = new HashMap<>();
    private BookService bookService;

    public CartService(BookService bookService) {
        this.bookService = bookService;
    }

    public Cart getCart(int customerId) throws CartNotFoundException {
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " does not exist.");
        }
        return cart;
    }

    public Cart createCart(int customerId) {
        Cart cart = new Cart(customerId, customerId);
        carts.put(customerId, cart);
        return cart;
    }

    public Cart addItemToCart(int customerId, CartItem item)
            throws CartNotFoundException, BookNotFoundException, OutOfStockException {
        Cart cart = getCart(customerId);
        Book book = bookService.getBook(item.getBookId());

        if (book.getStock() < item.getQuantity()) {
            throw new OutOfStockException("Not enough stock for book with ID " + item.getBookId());
        }

        // Check if item already exists in cart
        boolean itemExists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getBookId() == item.getBookId()) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cart.getItems().add(item);
        }

        return cart;
    }

    public Cart updateCartItem(int customerId, int bookId, int quantity)
            throws CartNotFoundException, BookNotFoundException, OutOfStockException {
        Cart cart = getCart(customerId);
        Book book = bookService.getBook(bookId);

        if (book.getStock() < quantity) {
            throw new OutOfStockException("Not enough stock for book with ID " + bookId);
        }

        for (CartItem item : cart.getItems()) {
            if (item.getBookId() == bookId) {
                item.setQuantity(quantity);
                return cart;
            }
        }

        throw new BookNotFoundException("Book with ID " + bookId + " not found in cart.");
    }

    public void removeItemFromCart(int customerId, int bookId)
            throws CartNotFoundException, BookNotFoundException {
        Cart cart = getCart(customerId);

        boolean removed = cart.getItems().removeIf(item -> item.getBookId() == bookId);

        if (!removed) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found in cart.");
        }
    }
}
