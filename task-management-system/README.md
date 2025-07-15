# Task Management System

## Overview
This project is a Task Management System built using Spring Boot, Hibernate, and SQL Server. It provides a RESTful API for managing users, roles, projects, tasks, and comments. The system supports JWT-based authentication and role-based authorization.

## Features
- User management with roles
- Project management
- Task management with comments
- JWT-based authentication
- Role-based authorization
- Centralized error handling
- Data validation for API requests

## Technology Stack
- **Backend Framework:** Spring Boot 3+
- **ORM:** Hibernate
- **Database:** SQL Server
- **Build Tool:** Maven
- **Security:** Spring Security (JWT authentication)

## Project Structure
```
task-management-system
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── taskmanagement
│   │   │               ├── TaskManagementSystemApplication.java
│   │   │               ├── config
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── UserController.java
│   │   │               │   ├── RoleController.java
│   │   │               │   ├── ProjectController.java
│   │   │               │   ├── TaskController.java
│   │   │               │   └── CommentController.java
│   │   │               ├── dto
│   │   │               │   └── (DTO classes)
│   │   │               ├── entity
│   │   │               │   ├── User.java
│   │   │               │   ├── Role.java
│   │   │               │   ├── Project.java
│   │   │               │   ├── Task.java
│   │   │               │   └── Comment.java
│   │   │               ├── exception
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── repository
│   │   │               │   ├── UserRepository.java
│   │   │               │   ├── RoleRepository.java
│   │   │               │   ├── ProjectRepository.java
│   │   │               │   ├── TaskRepository.java
│   │   │               │   └── CommentRepository.java
│   │   │               ├── security
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   ├── JwtTokenProvider.java
│   │   │               │   └── CustomUserDetailsService.java
│   │   │               └── service
│   │   │                   ├── UserService.java
│   │   │                   ├── RoleService.java
│   │   │                   ├── ProjectService.java
│   │   │                   ├── TaskService.java
│   │   │                   └── CommentService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── taskmanagement
│                       ├── service
│                       │   ├── UserServiceTest.java
│                       │   ├── RoleServiceTest.java
│                       │   ├── ProjectServiceTest.java
│                       │   ├── TaskServiceTest.java
│                       │   └── CommentServiceTest.java
│                       └── repository
│                           ├── UserRepositoryTest.java
│                           ├── RoleRepositoryTest.java
│                           ├── ProjectRepositoryTest.java
│                           ├── TaskRepositoryTest.java
│                           └── CommentRepositoryTest.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- SQL Server

### Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   cd task-management-system
   ```

2. Configure the database connection in `src/main/resources/application.properties`:
   ```
   spring.datasource.url=jdbc:sqlserver://<server>:<port>;databaseName=<database>
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the API at `http://localhost:8080/api`.

### API Documentation
Refer to the API endpoints section in the code for detailed information on available endpoints.

### Testing
Unit tests are provided for core services and repositories. Run tests using:
```
mvn test
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.