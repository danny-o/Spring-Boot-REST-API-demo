
# 🚀 Spring Boot Demo Project
A demo **Spring Boot** project that demonstrates how to build a secure, production-ready REST API.
## 🛠 Tech Stack

* **Framework:** Spring Boot 4.x
* **Language:** Java 25
* **Security:** Spring Security & JWT (JSON Web Tokens)
* **Database:** PostgreSQL
* **Migration:** Flyway
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Maven

---

## 🏗 Features

* **Full CRUD Operations:** Create, Read, Update, and Delete notes.
* **Secure Authentication:** Email/Password login with stateless JWT token issuance.
* **Database Versioning:** Flyway ensures schema consistency across environments.
* **Validation:** Input validation using Jakarta Bean Validation (e.g., description length, non-empty fields).
* **Containerized:** One-command setup for both the app and the database.

---

## 🚦 Getting Started

### Prerequisites
* Docker and Docker Compose installed.
* Java 25 (if running locally without Docker).

### Running with Docker (Recommended)
1. Clone the repository.
2. Build the jar file:
   ```bash
   ./mvnw clean package -DskipTests
   ```
3. Spin up the containers:
   ```bash
   docker-compose up --build
   ```
The application will be available at `http://localhost:8080`.

---

## 🔑 Authentication Flow

1.  **Register/Login:** Send a POST request to `/api/auth/login` with your credentials.
2.  **Receive Token:** The server returns a JWT string.
3.  **Authorize:** Include the token in the `Authorization` header for all protected endpoints:
    `Authorization: Bearer <your_token_here>`

---

## 📡 API Endpoints

### Auth
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authenticate user and return JWT |

### Notes (Protected)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/notes` | Get all notes for the authenticated user |
| `GET` | `/api/notes/{id}` | Get a specific note by ID |
| `POST` | `/api/notes` | Create a new note |
| `PUT` | `/api/notes/{id}` | Update an existing note |
| `DELETE` | `/api/notes/{id}` | Delete a note |

---

## 🗄 Database Migrations
Migrations are located in `src/main/resources/db/migration`.
* **V1__init.sql**: Creates the `notes` and `users` tables.
* **V2__...**: Subsequent schema changes.
  *Flyway handles the execution of these scripts automatically on startup.*

---

## 📝 Environment Variables
The following variables can be configured in the `docker-compose.yml` or `application.properties`:
* `SPRING_DATASOURCE_URL`: JDBC connection string.
* `JWT_SECRET`: Secret key for signing tokens.
* `POSTGRES_USER` / `POSTGRES_PASSWORD`: Database credentials.

---