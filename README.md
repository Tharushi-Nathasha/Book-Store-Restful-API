# 📚 Book Store RESTful API

This project implements a RESTful API for a Bookstore system using Java and the JAX-RS framework (Jersey). It enables client applications to perform CRUD operations on books, authors, customers, shopping carts, and orders. The API is stateless, follows REST principles, and uses JSON as the data exchange format.

---

## 🔧 Technologies Used

- **Java**
- **JAX-RS (Jersey Implementation)**
- **Maven**
- **JSON**
- **Java Collections (in-memory data)**
- **Postman (for testing)**

---

## 🧩 Core Functionalities

### 📘 Book Management
- Create, retrieve, update, and delete books
- Validate ISBN uniqueness and publication year

### ✍️ Author Management
- Full CRUD on authors
- Retrieve books by a specific author

### 👤 Customer Management
- Register and manage customers
- Basic authentication simulation

### 🛒 Cart Management
- Add and remove books in a shopping cart
- Update item quantities
- Linked to specific customers

### 📦 Order Processing
- Place an order from a cart
- Ensure stock validation and order history per customer

---

## 🚀 REST Endpoints Overview

| Resource  | Endpoint Pattern                        | Methods                     |
|-----------|------------------------------------------|-----------------------------|
| Books     | `/books`, `/books/{id}`                  | GET, POST, PUT, DELETE      |
| Authors   | `/authors`, `/authors/{id}`              | GET, POST, PUT, DELETE      |
| Customers | `/customers`, `/customers/{id}`          | GET, POST, PUT, DELETE      |
| Carts     | `/customers/{customerId}/cart`           | GET, POST, PUT, DELETE      |
| Orders    | `/customers/{customerId}/orders`         | GET, POST                   |

---

## ⚙️ Data Models

- **Book**: title, authorId, ISBN, year, price, stock
- **Author**: name, biography
- **Customer**: name, email, password
- **CartItem**: bookId, quantity
- **Cart**: list of CartItems
- **Order**: customerId, total price, order date, items

---

## 🧪 Testing Strategy

- Tests conducted using **Postman**
- Functional tests for all endpoints
- Validation tests for input rules (e.g., year, stock)
- Error handling tests using custom exceptions

---

## ⚠️ Exception Handling

- Custom exception classes:
  - `BookNotFoundException`
  - `AuthorNotFoundException`
  - `CustomerNotFoundException`
  - `InvalidInputException`
  - `OutOfStockException`
  - `CartNotFoundException`
- Each exception is mapped to a structured JSON response with appropriate HTTP status codes.

---

## 🏁 Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/Tharushi-Nathasha/Book-Store-Restful-API.git
   cd Book-Store-Restful-API
