/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore_restful_api;

/**
 *
 * @author MSI
 */
import com.mycompany.bookstore_restful_api.exception.CustomExceptionMapper;
import com.mycompany.bookstore_restful_api.resource.AuthorResource;
import com.mycompany.bookstore_restful_api.resource.BookResource;
import com.mycompany.bookstore_restful_api.resource.CartResource;
import com.mycompany.bookstore_restful_api.resource.CustomerResource;
import com.mycompany.bookstore_restful_api.resource.OrderResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();

        // Add resources
        resources.add(AuthorResource.class);
        resources.add(BookResource.class);
        resources.add(CartResource.class);
        resources.add(CustomerResource.class);
        resources.add(OrderResource.class);

        // Add JAX-RS features
        resources.add(CustomExceptionMapper.class);

        return resources;
    }
}
