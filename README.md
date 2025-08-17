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


### 3. Inventory Management
- **Get All Items** (`GET /api/items`) – All users
- **Add New Item** (`POST /api/items/add`) – Admin only
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


- `POST /api/auth/register` → Register new user (**Admin only**)  
 


---

## 🔒 Security
- **Role-based Authorization**:
    - `ADMIN`: Full access (Users + Items)
- **Password Encryption**: BCrypt

---

## ▶️ How to Run (Backend)

```bash
git clone https://github.com/your-repo/inventory-system-backend.git
cd inventory-system-backend

