# Todo Application

This is a simple Todo application built using Java Spring Boot and Thymeleaf.

## Features

- Create new todos
- Update existing todos
- Delete todos
- Search todos by title or ID
- Display all todos

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- JPA
- Tailwind (for styling)
- PostgreSQL

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Mahdi-Mnx/Todo_App.git
cd Todo_App
```

### Build the application

From the repository root, compile and package the project with Maven:

```bash
mvn clean package -DskipTests
```

This produces the application JAR in `target/`. Configure a local PostgreSQL database before running the application or database-dependent tests.
