# Payment Management System

A comprehensive Spring Boot-based payment management system with role-based authentication, payment processing, and robust security features.

## 🚀 Features

- **User Management** with Role-based Access Control (RBAC)
- **Payment Processing** (Incoming/Outgoing payments)
- **JWT Token-based Authentication**
- **RESTful API Design**
- **PostgreSQL Database Integration**
- **Comprehensive Test Coverage** with JaCoCo
- **Input Validation** and Error Handling
- **Audit Logging** for payment transactions

## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| **Backend** | Spring Boot 3.x, Spring Security, Spring Data JPA |
| **Database** | PostgreSQL 13+ |
| **Authentication** | JWT (JSON Web Tokens) |
| **Testing** | JUnit 5, Mockito, TestContainers |
| **Build Tool** | Maven 3.6+ |
| **Code Coverage** | JaCoCo |
| **Documentation** | Swagger/OpenAPI 3 |

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Git

## 🏗️ System Architecture

### Class Diagram

```mermaid
classDiagram
    class User {
        -Long id
        -String name
        -String email
        -String password
        -UserRole role
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        +getId() Long
        +getName() String
        +getEmail() String
        +getRole() UserRole
    }

    class Payment {
        -Long id
        -BigDecimal amount
        -PaymentType paymentType
        -PaymentCategory category
        -PaymentStatus status
        -Long userId
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        +getId() Long
        +getAmount() BigDecimal
        +getPaymentType() PaymentType
        +getCategory() PaymentCategory
        +getStatus() PaymentStatus
    }

    class UserController {
        -UserService userService
        +createUser(UserRequest) ResponseEntity
        +getAllUsers() ResponseEntity
        +getUserById(Long) ResponseEntity
        +updateUser(Long, UserRequest) ResponseEntity
        +deleteUser(Long) ResponseEntity
    }

    class PaymentController {
        -PaymentService paymentService
        +createPayment(PaymentRequest) ResponseEntity
        +getAllPayments() ResponseEntity
        +getPaymentById(Long) ResponseEntity
        +updatePayment(Long, PaymentRequest) ResponseEntity
        +deletePayment(Long) ResponseEntity
    }

    class AuthController {
        -AuthService authService
        +login(LoginRequest) ResponseEntity
        +register(RegisterRequest) ResponseEntity
    }

    class UserService {
        -UserRepository userRepository
        -PasswordEncoder passwordEncoder
        +createUser(UserRequest) UserResponse
        +getAllUsers() List~UserResponse~
        +getUserById(Long) UserResponse
        +updateUser(Long, UserRequest) UserResponse
        +deleteUser(Long) void
    }

    class PaymentService {
        -PaymentRepository paymentRepository
        -UserRepository userRepository
        +createPayment(PaymentRequest) PaymentResponse
        +getAllPayments() List~PaymentResponse~
        +getPaymentById(Long) PaymentResponse
        +updatePayment(Long, PaymentRequest) PaymentResponse
        +deletePayment(Long) void
    }

    class AuthService {
        -UserRepository userRepository
        -PasswordEncoder passwordEncoder
        -JwtTokenProvider jwtTokenProvider
        +authenticate(LoginRequest) AuthResponse
        +register(RegisterRequest) AuthResponse
    }

    class UserRole {
        <<enumeration>>
        ADMIN
        FINANCE_MANAGER
        VIEWER
    }

    class PaymentType {
        <<enumeration>>
        INCOMING
        OUTGOING
    }

    class PaymentCategory {
        <<enumeration>>
        SALARY
        VENDOR
        INVOICE
        INVESTMENT
    }

    class PaymentStatus {
        <<enumeration>>
        PENDING
        COMPLETED
        FAILED
        CANCELLED
    }

    UserController --> UserService
    PaymentController --> PaymentService
    AuthController --> AuthService
    User ||--|| UserRole
    Payment ||--|| PaymentType
    Payment ||--|| PaymentCategory
    Payment ||--|| PaymentStatus
    Payment }o--|| User : belongs to

```

Swagger file Link :- [Swagger Link](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/swarnimsrijan/Mini-Projects/refs/heads/main/PaymentManagementSystemUsingSpringBoot/Swagger.yml)
