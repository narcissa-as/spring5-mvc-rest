# **Spring MVC REST Application**

## **Overview**

This repository contains a **Java Spring MVC application** that demonstrates how to design and expose **RESTful APIs** using the Spring Framework. The project focuses on clean layering, request/response handling, data serialization, and REST best practices commonly used in professional backend systems.

The codebase is structured to reflect **real-world Spring MVC applications**, emphasizing clarity, maintainability, and separation of concerns.

---

## **Key Features**

* **RESTful API design** using Spring MVC
* **Controller–Service architecture**
* **HTTP request mapping** with `@GetMapping`, `@PostMapping`, etc.
* **JSON serialization/deserialization** using Jackson
* **Request validation and response handling**
* **Exception handling strategy** for REST APIs
* **Maven-based project structure**

---

## **Technology Stack**

* **Java**
* **Spring Framework 5 (Spring MVC)**
* **Spring Web**
* **RESTful API design**
* **Jackson** (JSON serialization/deserialization)
* **Lombok** (boilerplate reduction)
* **Apache Maven**
* **JUnit** (testing support)

---

## **Project Structure**

```text
src/main/java
 └── com.example.spring5mvcrest
     ├── controller
     │   └── RestController.java
     ├── service
     │   ├── ApiService.java
     │   └── ApiServiceImpl.java
     ├── model
     │   └── DomainObject.java
     └── SpringMvcRestApplication.java

src/main/resources
 └── application.properties
```

---

## **REST API Design & Implementation Details**

* **@RestController** for exposing REST endpoints
* **Request mapping annotations** (`@GetMapping`, `@PostMapping`, etc.)
* **DTO / domain object mapping** for clean request and response models
* **JSON-based communication** using Jackson
* **Centralized exception handling** using `@ControllerAdvice`
* **HTTP status management** with `ResponseEntity`
* **Layered architecture** (Controller → Service → Domain)

---

## **Running the Application**

### **Prerequisites**

* **Java 11 or higher**
* **Maven**

### **Run Locally**

```bash
mvn clean spring-boot:run
```

Once started, the REST endpoints are available on the configured local port.

---

## **Build**

```bash
mvn clean package
```

---

## **Testing**

* Includes **unit testing dependencies**
* Designed to support **controller and service layer testing**

---

## **Purpose**

This project demonstrates how to build **clean, maintainable REST APIs** using **Spring MVC**. It highlights common backend patterns such as layered architecture, request mapping, and JSON-based communication that are expected in professional Java backend roles.

---

## **Notes**

* The project is intentionally focused on **core REST concepts**
* Suitable as a **reference implementation** or portfolio project for backend development
