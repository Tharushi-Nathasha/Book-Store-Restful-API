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
import com.mycompany.bookstore_restful_api.exception.BookNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private static Map<Integer, Book> books = new HashMap<>();
    private static int nextId = 1;

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book getBook(int id) throws BookNotFoundException {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        return book;
    }

    public Book addBook(Book book) {
        book.setId(nextId++);
        books.put(book.getId(), book);
        return book;
    }

    public Book updateBook(int id, Book book) throws BookNotFoundException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        book.setId(id);
        books.put(id, book);
        return book;
    }

    public void deleteBook(int id) throws BookNotFoundException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " does not exist.");
        }
        books.remove(id);
    }
}
