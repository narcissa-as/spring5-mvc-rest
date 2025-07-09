# 📦 Spring5 MVC REST API

A sample RESTful API built using Spring Boot 2, Spring MVC, and Spring Data JPA — based on the *Spring Framework 5: Beginner to Guru* course. This project demonstrates basic CRUD operations, RESTful principles, exception handling, and integration with an H2 in-memory database.

---

## 🚀 Features

- RESTful CRUD APIs for `Customer` and `Vendor`
- Custom exception handling with `@ControllerAdvice`
- Data persistence using Spring Data JPA and H2
- DTO pattern to decouple entities from API
- Bootstrap data on application startup
- Maven-based build

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 2.x
- Spring MVC
- Spring Data JPA
- Lombok
- H2 In-Memory Database
- Maven

---

## 📁 Project Structure

src/
├── main/
│ ├── java/com/example/springmvcrest/
│ │ ├── controllers/
│ │ ├── domain/
│ │ ├── repositories/
│ │ ├── services/
│ │ └── bootstrap/
│ └── resources/
│ └── application.properties

yaml
Copy
Edit

---

## 🧪 Running the Project

### Prerequisites:
- Java 17+
- Maven 3+

### Run using Maven:
```bash
mvn spring-boot:run
Or run the packaged JAR:
bash
Copy
Edit
mvn clean package
java -jar target/spring5-mvc-rest-0.0.1-SNAPSHOT.jar
Application will start at:
http://localhost:8080/api/v1/customers
http://localhost:8080/api/v1/vendors

📡 API Endpoints
🔹 Customer Controller
Method	Endpoint	Description
GET	/api/v1/customers	List all customers
GET	/api/v1/customers/{id}	Get customer by ID
POST	/api/v1/customers	Create new customer
PUT	/api/v1/customers/{id}	Update customer
PATCH	/api/v1/customers/{id}	Partial update
DELETE	/api/v1/customers/{id}	Delete customer

🔹 Vendor Controller
Method	Endpoint	Description
GET	/api/v1/vendors	List all vendors
GET	/api/v1/vendors/{id}	Get vendor by ID
POST	/api/v1/vendors	Create new vendor
PUT	/api/v1/vendors/{id}	Update vendor
PATCH	/api/v1/vendors/{id}	Partial update
DELETE	/api/v1/vendors/{id}	Delete vendor

❗ Error Handling
The project uses @ControllerAdvice to handle exceptions globally.
Example error response when a resource is not found:

json
Copy
Edit
{
  "message": "Customer not found",
  "status": 404
}
💾 Database
Uses H2 in-memory database

Sample data is loaded automatically on startup

Access H2 console at:
http://localhost:8080/h2-console
(JDBC URL: jdbc:h2:mem:testdb)

🧪 Future Improvements
Add unit and integration tests

Add Swagger documentation

Convert to OpenAPI 3 specification

Add Docker support

📄 License
This project is part of personal learning based on Spring Framework 5: Beginner to Guru
Available under MIT license. 

