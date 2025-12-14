# **Spring REST Client Examples**

## **Overview**

This project is a **Java Spring Boot application** that demonstrates how to consume external **REST APIs** using different client approaches provided by the Spring ecosystem. It includes practical examples of **synchronous** and **reactive** HTTP communication, data mapping, error handling, and simple UI rendering.

The application is structured as a **clean, production-style Spring Boot project** and can be used as a reference for building REST clients in real-world backend systems.

## **Key Features**

* **Consumption of external REST APIs** using Spring REST clients
* **Synchronous HTTP calls** with `RestTemplate`
* **Reactive HTTP calls** with `WebClient` (Spring WebFlux)
* **Centralized REST client configuration**
* **Domain-driven API response mapping**
* **Service and controller separation**
* **Basic UI rendering** using Thymeleaf
* **Maven-based build and dependency management**

## **Technology Stack**

* **Java**
* **Spring Boot 2.7.x**
* **Spring Web (RestTemplate)**
* **Spring WebFlux (WebClient)**
* **Spring MVC**
* **Thymeleaf**
* **Lombok**
* **Jackson** (JSON serialization/deserialization)
* **Apache HttpClient**
* **Bootstrap** (via WebJars)
* **Maven**
* **JUnit / TestNG / Reactor Test**

## **Project Structure**

```text
src/main/java
 └── nas.springframework.springrestclientexamples5
     ├── config
     │   └── RESTTemplateConfig.java
     ├── controller
     │   ├── APIFruitsController.java
     │   └── UserController.java
     ├── service
     │   ├── APIService.java
     │   ├── APIServiceImpl.java
     │   ├── APIFruitService.java
     │   └── APIFruitServiceImpl.java
     ├── api/domain
     │   ├── Customer.java
     │   ├── CustomerData.java
     │   ├── User.java
     │   └── UserData.java
     ├── RestTemplateExamples.java
     └── SpringRestClientExamples5Application.java

src/main/resources
 ├── templates
 └── application.properties
```

## **REST Client Implementation**

### **RestTemplate**

* Used for **synchronous HTTP communication**
* Demonstrates **GET requests**, header configuration, URI building, and error handling
* Configured via a **dedicated configuration class**

### **WebClient (Reactive)**

* Used for **non-blocking, reactive HTTP communication**
* Suitable for **modern, scalable applications**
* Integrated via **Spring WebFlux**

## **External APIs**

The application consumes **publicly available REST APIs** to demonstrate real HTTP interactions, response parsing, and domain mapping.

## **Running the Application**

### **Prerequisites**

* **Java 11 or higher**
* **Maven**

### **Steps**

1. Clone the repository
2. Navigate to the project root
3. Run the application:

```bash
mvn spring-boot:run
```

4. Access the application via browser or REST endpoints once the server is running

## **Build**

```bash
mvn clean package
```

## **Testing**

The project includes **test dependencies and examples** for validating REST client behavior using standard Spring testing tools.

## **Purpose**

This project showcases **practical patterns** for consuming RESTful services in **Spring-based applications**. It reflects **clean layering**, proper client configuration, and **multiple HTTP client strategies** commonly used in professional backend development.

## **Notes**

* The codebase is intentionally kept **simple and focused** on REST client usage
* Suitable as a **reference project or starter template** for backend integrations
