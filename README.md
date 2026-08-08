<div align="center">

# 🛠️ Store Backend API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-for-the-badge&logo=openjdk&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/Security-Spring_Security_--_JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

<p>RESTful API robust built with Spring Boot for managing Products, Categories, Users, and Product Reviews.</p>

---

</div>

## 📌 Features

- **🛍️ Product Management:** CRUD operations for products, images, and categories.
- **⭐ Review System:** Users can submit ratings (1-5 stars) and comments on products.
- **🔐 Security & Auth:** User authentication and authorization using Spring Security with JWT & User Roles.
- **🗄️ Relational Mapping:** Clean JPA/Hibernate mappings (`@OneToMany`, `@ManyToOne`, `@ManyToMany`) between Users, Products, Categories, and Reviews.

---

## 🏗️ Architecture & Database Schema

### Entity Relations (JPA/Hibernate)
- **`Product` ↔ `Review`:** `@OneToMany` (One product has many reviews).
- **`AppUser` ↔ `Review`:** `@OneToMany` (One user can write many reviews).
- **`AppUser` ↔ `AppUserRole`:** `@ManyToMany` (Users have defined access roles).
- **`Product` ↔ `Category`:** `@ManyToOne` (Products belong to a category).

---

## ⚙️ Requirements & Dependencies

| Tech | Version |
| :--- | :--- |
| **Java JDK** | 17 or higher |
| **Spring Boot** | 3.x |
| **Build Tool** | Maven |
| **Database** | MySQL / PostgreSQL |

---

## 🚀 Getting Started

### 1. Database Setup
Create a new SQL database (e.g., MySQL):
```sql
CREATE DATABASE ecommercedb;

spring.application.name=demo

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/store_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true