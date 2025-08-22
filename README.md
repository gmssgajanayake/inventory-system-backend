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

### 2. User Management (Admin Only)
- **Get All Users** (`GET /api/users`) – Admin only
- **Update User Role** (`PUT /api/users/{id}`) – Admin only
- **Delete User** (`DELETE /api/users/{id}`) – Admin only
- Role-based access control (Admin, Staff)


### 3. Inventory Management
- **Get All Items** (`GET /api/items`) – All users
- **Add New Item** (`POST /api/items/add`) – Admin only
- **Delete Item** (`DELETE /api/items/{id}`) – Admin only
- **Update Item** (`PUT /api/items/{id}`) – Admin & Staff
- Staff can only update item quantity, Admin can update all details

---

## 🔒 Security
- **Role-based Authorization**:
- `ADMIN`: Full access (Users + Items)
- **Password Encryption**: BCrypt

---

## API Endpoints 🌐

**Base URL:** `/api`

### Authentication

| Method | Endpoint         | Description                      | Access     |
| ------ | ---------------- | -------------------------------- | ---------- |
| POST   | `/auth/register` | Register a new user              | Admin Only |
| POST   | `/auth/login`    | Authenticate user and return JWT | All Users  |

### User Management

| Method | Endpoint      | Description      | Access     |
| ------ | ------------- | ---------------- | ---------- |
| GET    | `/users`      | List all users   | Admin Only |
| PUT    | `/users/{id}` | Update user role | Admin Only |
| DELETE | `/users/{id}` | Delete a user    | Admin Only |

### Inventory Management

| Method | Endpoint      | Description                                      | Access        |
| ------ | ------------- | ------------------------------------------------ | ------------- |
| GET    | `/items`      | List all items                                   | All Users     |
| POST   | `/items`      | Add a new inventory item                         | Admin Only    |
| PUT    | `/items/{id}` | Update item details (Admins) or quantity (Staff) | Admin & Staff |
| DELETE | `/items/{id}` | Delete an item                                   | Admin Only    |

---

## Database Schema 📊

### `users` Table

| Field    | Type                   | Notes            |
| -------- | ---------------------- | ---------------- |
| id       | BIGINT (PK)            | Auto Increment   |
| username | VARCHAR                | Unique           |
| password | VARCHAR                | BCrypt encrypted |
| role     | ENUM('ADMIN', 'STAFF') | User role        |

### `items` Table

| Field       | Type          | Notes          |
| ----------- | ------------- | -------------- |
| id          | BIGINT (PK)   | Auto Increment |
| name        | VARCHAR       | Unique         |
| description | TEXT          | Optional       |
| quantity    | INT           | Default 0      |
| price       | DECIMAL(10,2) | Optional       |

---

## Setup and Installation ⚙️ 

1. **Clone the repository**

```bash
git clone https://github.com/gmssgajanayake/inventory-system-backend.git
cd simple-inventory-system-backend
```

2. **Create MySQL database**

```sql
CREATE DATABASE inventory_db;
```

3. **Configure database connection**\
   Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
```

4. **Build and run**

```bash
mvn clean install
mvn spring-boot:run
```
---

## Initial Admin User 👤

An **admin user is now auto-generated** at initial setup.

- **Username:** `admin`
- **Password:** `admin123`

You don’t need to create an initial admin user manually in MySQL anymore.

> Note: The default admin (`ADMIN`) is only for initial access.  
> It is strongly recommended to **create a new admin account** and then **delete the default one** for better security.


Application runs at: `http://localhost:8080/auth/login`

---



## Sample Requests 📝

### Register User (Admin Only)

```json
POST /api/auth/register
{
  "username": "staff1",
  "password": "password123",
  "role": "ADMIN"
}
```

### Login User

```json
POST /api/auth/login
{
  "username": "admin",
  "password": "adminpassword"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Add Inventory Item (Admin Only)

```json
POST /api/items
{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "quantity": 10,
  "price": 1200.50
}
```

### Update Item Quantity (Admin & Users - Users can only update quantity )

```json
PUT /api/items/{id}
{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "quantity": 10,
  "price": 1200.50
}
```

---

## Notes

- Make sure **MySQL server is running** before starting the application
- Use **Postman** to test API endpoints
- JWT token must be included in **Authorization header**: `Bearer <token>`

---