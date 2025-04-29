/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api.service;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.model.Customer;
import com.mycompany.bookstore_restful_api.exception.CustomerNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static int nextId = 1;

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomer(int id) throws CustomerNotFoundException {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        customer.setId(nextId++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer updateCustomer(int id, Customer customer) throws CustomerNotFoundException {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    public void deleteCustomer(int id) throws CustomerNotFoundException {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        customers.remove(id);
    }
}
