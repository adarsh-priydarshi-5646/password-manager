# Project Overview

This is a web-based password manager application built with Java and Spring Boot. It provides a secure way to store and manage passwords. The application features a modern user interface built with Tailwind CSS and uses a PostgreSQL database for data persistence. It also includes a RESTful API for programmatic access.

**Key Technologies:**

*   **Backend:** Java 21, Spring Boot 3.5.5, Spring Data JPA, Spring HATEOAS, Spring Web, Thymeleaf, Lombok
*   **Frontend:** Tailwind CSS 4.0.17, Thymeleaf
*   **Database:** PostgreSQL 17, H2 (for development/testing)
*   **Build/Dependency Management:** Maven
*   **Containerization:** Docker Compose

# Building and Running

**Prerequisites:**

*   Java 21 or higher
*   Maven 3.6+ (or use the included Maven wrapper)
*   Docker & Docker Compose (for the database)

**Build and Run Commands:**

1.  **Start the PostgreSQL database:**
    ```bash
    docker-compose up -d postgres
    ```

2.  **Build the frontend and compile the project:**
    ```bash
    ./mvnw clean compile
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Access the application:**
    *   Web Interface: [http://localhost:8080](http://localhost:8080)
    *   REST API: [http://localhost:8080/api/v1/vault/all](http://localhost:8080/api/v1/vault/all)

**Testing:**

*   Run the test suite:
    ```bash
    ./mvnw test
    ```

# Development Conventions

*   **Code Style:** The project follows standard Java conventions.
*   **Testing:** The project uses JUnit for testing. The existing tests are in `src/test/java`.
*   **Frontend Development:**
    *   The frontend source files are located in `src/main/frontend`.
    *   To watch for changes and automatically rebuild the CSS, run the following command in the `src/main/frontend` directory:
        ```bash
        npm run watch
        ```
*   **Database Schema:** The database schema is defined by the `VaultItem` entity in `src/main/java/ua/com/javarush/parse/m5/passwordmanager/entity/VaultItem.java`.
*   **Soft Deletes:** The application uses a soft delete mechanism for data recovery.
