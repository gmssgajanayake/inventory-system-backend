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
- Role-based access to APIs (`ADMIN`, `STAFF`)

### 2. User Management (Admin only)
- **Get All Users** (`GET /api/users`)
- **Update User Role** (`PUT /api/users/{id}`)
- **Delete User** (`DELETE /api/users/{id}`)

### 3. Inventory Management
- **Get All Items** (`GET /api/items`) – All users
- **Add New Item** (`POST /api/items/add`) – Admin only
- **Update Item Details** (`PUT /api/items/{id}`) – Admin (full update), Staff (quantity only)
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

## ⚡ API Endpoints (Backend)

### Authentication
- `POST /api/auth/register` → Register new user (**Admin only**)
- `POST /api/auth/login` → Login and get JWT

### User Management (Admin only)
- `GET /api/users` → Get all users
- `PUT /api/users/{id}` → Update user role
- `DELETE /api/users/{id}` → Delete user

### Inventory Management
- `GET /api/items` → Get all items (All)
- `POST /api/items/add` → Add new item (Admin only)
- `PUT /api/items/{id}` → Update item (Admin full update, Staff quantity update)
- `DELETE /api/items/{id}` → Delete item (Admin only)

---

## 🔒 Security
- **JWT Authentication** for login
- **Role-based Authorization**:
    - `ADMIN`: Full access (Users + Items)
    - `STAFF`: Limited access (View items, update stock quantity only)
- **Password Encryption**: BCrypt

---

## ▶️ How to Run (Backend)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/inventory-system-backend.git
   cd inventory-system-backend

Do you want me to also include **sample JSON request/response payloads** for each endpoint in the README so it’s easier to test with Postman?
