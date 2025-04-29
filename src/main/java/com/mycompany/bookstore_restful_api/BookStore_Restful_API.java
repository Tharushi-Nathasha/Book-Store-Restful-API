package com.mycompany.bookstore_restful_api;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;  // Corrected import
import org.glassfish.jersey.servlet.ServletContainer;

public class BookStore_Restful_API {

   public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);

        // Corrected package path
        jerseyServlet.setInitParameter(
                "javax.ws.rs.Application",
                "com.mycompany.bookstore_restful_api.ApplicationConfig"
        );

        server.setHandler(context);

        try {
            server.start();
            System.out.println("Server running! Access endpoints at http://localhost:8080/api/");
            System.out.println("Example: http://localhost:8080/api/books");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
