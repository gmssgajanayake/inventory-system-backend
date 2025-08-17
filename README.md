# 📦  Inventory Management System - Backend

This is the **Spring Boot backend** for the **Simple Inventory Management System**.  
It provides REST APIs for authentication, user management, and inventory management.  
The backend is secured with **Spring Security + JWT** and supports **Role-Based Access Control (RBAC)**.

---

## 🚀 Technology Stack
- **Backend Framework**: Spring Boot
- **ORM & Persistence**: Hibernate (JPA)
- **Authentication & Authorization**: Spring Security, JWT
- **Database**: MySQL
- **Build Tool**: Maven

---

## 🔑 Features

### 1. Authentication & Authorization
- **User Registration** (`/api/auth/register`) – Admin only
- **User Login** (`/api/auth/login`) – All users
- JWT-based authentication
- Role-based access to APIs (`ADMIN`, `USER`)

### 2. User Management 
- **Get All Users** (`GET /api/users`) – Admin only
- **Update User Role** (`PUT /api/users/{id}`) – Admin only
- **Delete User** (`DELETE /api/users/{id}`) – Admin only

### 3. Inventory Management
- **Get All Items** (`GET /api/items`) – All users
- **Add New Item** (`POST /api/items/add`) – Admin only
- **Update Item Details** (`PUT /api/items/{id}`) – Admin (full update), User (quantity only)
- **Delete Item** (`DELETE /api/items/{id}`) – Admin only

---

## 📂 Project Structure (Backend Only)


---

## 🗄️ Database Design

### Users Table
| Field     | Type            | Notes                         |
|-----------|----------------|-------------------------------|
| id        | BIGINT (PK)    | Auto Increment                |
| username  | VARCHAR        | Unique                        |
| password  | VARCHAR        | BCrypt Encrypted              |
| role      | ENUM           | `ADMIN` / `STAFF`             |

### Items Table
| Field       | Type            | Notes                         |
|-------------|----------------|-------------------------------|
| id          | BIGINT (PK)    | Auto Increment                |
| name        | VARCHAR        | Unique                        |
| description | TEXT           | Optional                      |
| quantity    | INT            | Default 0                     |
| price       | DECIMAL(10,2)  | Optional                      |

---


# ⚡ API Endpoints (Backend)
First login then get JWT token for atherization.
User JWT token is required for some endpoints to access.

## Authentication & Authorization
- `POST /api/auth/login` → Login user (**All users**)  
  **Request Body (JSON):**
  ```json
  {
    "username": "user",
    "password": "user1234",
  }


- `POST /api/auth/register` → Register new user (**Admin only**)  
  **Request Body (JSON):**
  ```json
  {
    "username": "admin",
    "password": "admin1234",
    "role": "ADMIN" 
  }  

## User Management
- `GET /api/users` → Get all users details (**Admin only**)  
 

- `PUT /api/users/{id}` → Update user role (**Admin only**)  
  **Request Body (JSON):**
  ```json
  {
    "username": "admin",
    "password": "admin1234",
    "role": "USER"
  }

- `DELETE /api/users/{id}` → Delete user (**Admin only**)  
 
## Inventory Management
- `GET /api/items` → Get all items (**All users**)  
  


- `POST api/items/add` → Item add (**Admin only**)  
  **Request Body (JSON):**
  ```json
   {
    "name": "Laptop ",
    "description": "Apple MacBook Pro",
    "quantity": 2 ,
    "price": 280000.00
    }


- `PUT api/items/{id}` → Item add (**Admin** - full update, **User** - Quantity only)  
  **Request Body (JSON):**
  ```json
    {
    "name": "Laptop ",
    "description": "Apple MacBook",
    "quantity": 4 ,
    "price": 220000.00
    }

- `DELETE api/items/{id}` → Item delete (**Admin only**)  

---

## 🔒 Security
- **JWT Authentication** for secure access
- **Role-based Authorization**:
    - `ADMIN`: Full access (Users + Items)
    - `USER`: Limited access (View items, update stock quantity only)
- **Password Encryption**: BCrypt

---


## ▶️ How to Run (Backend)

### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/inventory-system-backend.git
cd inventory-system-backend
```

### 2. Configure MySQL Database
Before running the application, update the database connection properties in `application.properties` or `application.yml`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Spring Boot Application
```bash
./mvnw spring-boot:run
```


### 4. Create an Admin User
Before calling any API endpoints, you need to create an admin user in the MySQL database. You can use the following SQL query:

```sql
INSERT INTO users (username, password, role) 
VALUES ('admin', 'admin1234', 'ADMIN');
```
> **Note:** Make sure the password is encoded according to your Spring Security configuration (e.g., BCrypt).

### 5. Test API with Login
You can now call other endpoints using **JWT Token**.

**Sample JSON for login:**
```json
{
  "username": "admin",
  "password": "admin1234"
}
```


