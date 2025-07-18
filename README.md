# 🏥 Patient Management Microservices System

This is a microservices-based **Patient Management System** built with **Spring Boot**, using technologies like **gRPC**, **Kafka**, **PostgreSQL**, and **JWT-based authentication**. All services are Dockerized and run within an internal Docker network.

---

## 🤩 Services Overview

| Service               | Description                                                                                                          |
| --------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **api-gateway**       | Entry point for all client requests. Handles routing and authentication.                                             |
| **auth-service**      | Manages user authentication, login, JWT token generation & validation.                                               |
| **patient-service**   | Handles patient creation, update, and deletion. Sends gRPC to billing-service and Kafka events to analytics-service. |
| **billing-service**   | Receives gRPC calls from patient-service (used for learning purposes).                                               |
| **analytics-service** | Kafka consumer that processes patient-related events.                                                                |

> All services are deployed as Docker containers and use internal Docker networking to communicate.

---
## 🚀 Technologies Used

- Java, Spring Boot
- Spring Cloud Gateway
- Kafka, gRPC
- PostgreSQL
- Swagger / OpenAPI
- Docker

---

## 🔐 Authentication Flow

- All requests pass through `api-gateway`.
- `auth-service` authenticates users and provides JWT tokens.
- Gateway validates JWT via a custom `JwtValidation` filter.
- Unauthorized requests are rejected with `401` without reaching backend services.

---
## 📂 API Documentation

Each service exposes Swagger documentation at:

```
GET /api-docs/patients → patient-service
GET /api-docs/auth     → auth-service
```
