# Banking Management System

A backend banking application built using **Java and Spring Boot** that provides RESTful APIs for managing bank accounts and performing essential banking operations.

## 🚀 Features

- **Account Management:** Create, retrieve, update, and manage bank accounts.
- **RESTful APIs:** Provides HTTP endpoints for banking operations.
- **Transaction Management:** Supports banking transaction operations (as implemented).
- **API Documentation:** Interactive API documentation using Swagger UI.
- **Database Integration:** Persistent storage for banking data.
- **Exception Handling:** Handles application errors and invalid requests.

## 🛠️ Technologies Used

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **PostgreSQL**
- **Swagger / OpenAPI**
- **REST APIs**

## 📂 Project Structure

```text
banking-platform/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bank/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       └── ...
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## ⚙️ Prerequisites

Make sure you have installed:

- JDK 17 or the Java version configured in the project
- IntelliJ IDEA
- Git
- Maven (optional, if using Maven Wrapper)
- Configured database

## 💻 Installation and Setup

**1. Clone the repository**

```bash
git clone https://github.com/YOUR_USERNAME/banking-platform.git
```

**2. Navigate to the project directory**

```bash
cd banking-platform
```

**3. Configure the database**

Update the database connection details in:

```text
src/main/resources/application.properties
```

**4. Run the application**

On Windows:

```bash
.\mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## 📖 API Documentation

This project uses Swagger UI for interactive REST API documentation.

After starting the application, access:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger UI allows you to explore available endpoints and test API requests.

## 🧪 API Testing

You can test the REST APIs using:

- Swagger UI
- Postman

## 🎯 Learning Outcomes

- Understanding Spring Boot application architecture.
- Developing RESTful APIs using Java.
- Implementing layered architecture.
- Working with database integration using Spring Data JPA.
- Understanding API documentation and testing.
- Managing source code using Git and GitHub.

## 👨‍💻 Author

**Sai Ganesh Reddy**

GitHub: [https://github.com/SaiGaneshReddy22/Banking-Management-System](https://github.com/SaiGaneshReddy22/Banking-Management-System)

---

⭐ If you find this project useful, feel free to star the repository!
