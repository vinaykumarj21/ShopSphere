# ShopSphere 🛍️

## A Robust Microservices-Based E-Commerce Platform

![ShopSphere Logo](https://github.com/vinaykumarj21/ShopSphere/blob/main/ShopSphereArchitecture.png)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7+-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue.svg)](https://microservices.io/)

## 📋 Table of Contents

- [Introduction](#introduction)
- [Key Features](#key-features)
- [System Architecture](#system-architecture)
- [Technology Stack](#technology-stack)
- [Microservices Overview](#microservices-overview)
- [Performance Optimizations](#performance-optimizations)
- [Deployment](#deployment)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)

## 🚀 Introduction

ShopSphere is a comprehensive e-commerce platform built using a microservices architecture. The platform provides a scalable, resilient, and maintainable system that enables seamless shopping experiences while ensuring high availability and performance. By leveraging the power of distributed systems and cloud computing, ShopSphere can handle high traffic loads and scale individual components as needed.

## ✨ Key Features

- **Secure Authentication & Authorization**: JWT-based authentication with role-based access control
- **Product Catalog**: Comprehensive product listing with advanced search capabilities
- **Payment Processing**: Seamless integration with Razorpay payment gateway
- **Notification System**: Event-driven email and SMS notifications using Kafka
- **Service Discovery**: Dynamic service registration and discovery using Eureka
- **Advanced Product Discovery**: Powerful sorting, filtering, and pagination
- **Optimized Performance**: Redis caching for improved response times
- **Cloud Deployment**: Full AWS cloud deployment with monitoring and logging

## 🏛️ System Architecture

The platform is built on a microservices architecture that ensures loose coupling, high cohesion, and independent scalability of components.

![Architecture Diagram](https://github.com/vinaykumarj21/ShopSphere/blob/main/ShopSphereArchitecture.png)

### Communication Patterns

- **Synchronous Communication**: REST APIs for direct service-to-service communication
- **Asynchronous Communication**: Kafka event streaming for non-blocking operations
- **Service Discovery**: Eureka for dynamic service registration and discovery

## 💻 Technology Stack

### Backend
- **Framework**: Spring Boot 2.7+
- **Cloud**: Spring Cloud Netflix (Eureka)
- **Security**: Spring Security with JWT
- **Database**: MySQL with Hibernate/JPA
- **Caching**: Redis
- **Messaging**: Apache Kafka
- **Testing**: JUnit, Mockito
- **SMS Gateway**: Twilio API

### Infrastructure
- **Deployment**: AWS Elastic Beanstalk
- **Database**: AWS RDS (MySQL)
- **Monitoring**: AWS CloudWatch
- **CI/CD**: GitHub Actions

## 🧩 Microservices Overview

### 1. Product Service
- Manages product catalog and inventory
- Provides product search, filtering, and recommendation features
- Implements caching for improved performance

### 2. User Authorization Service
- Handles user registration, authentication, and profile management
- Implements JWT-based authentication
- Manages role-based access control

### 3. Payment Gateway Service
- Integrates with Razorpay for payment processing
- Manages payment workflows and status updates
- Handles payment verification and security

### 4. Email Notification Service
- Processes notification events from Kafka
- Sends transactional emails (order confirmations, shipping updates, etc.)
- Provides templates for various notification types

### 5. SMS Notification Service
- Consumes SMS events from Kafka topics
- Integrates with Twilio for sending SMS notifications
- Supports order updates, shipping notifications, and promotional messages
- Implements retry mechanisms for failed SMS deliveries

### 6. Service Discovery (Eureka)
- Enables dynamic service registration and discovery
- Facilitates load balancing across service instances
- Supports system resilience through failover mechanisms

## ⚡ Performance Optimizations

### Redis Caching
- Reduced API response times from ~500ms to ~20ms
- Implemented for static data and frequently accessed resources
- Cache invalidation strategies for data consistency

### Kafka-based Event Processing
- Asynchronous processing for non-critical operations
- Improved system throughput for email and SMS notifications
- Enhanced scalability through event-driven architecture

## ☁️ Deployment

ShopSphere is deployed on AWS cloud infrastructure:

- **Application Hosting**: AWS Elastic Beanstalk for automated deployment and scaling
- **Database**: AWS RDS for managed MySQL database services
- **Monitoring**: AWS CloudWatch for logs, metrics, and alerts
- **Cache**: ElastiCache for Redis implementation

## 🏁 Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+
- Docker & Docker Compose
- MySQL 8.0+
- Kafka 2.6+
- Redis 6.0+
- Twilio Account (for SMS service)

### Local Setup

1. **Clone the repository**
```bash
git clone https://github.com/vinaykumarj21/ShopSphere.git
cd ShopSphere
```
2. **Configure environment variables**
```bash
# Create a .env file with the following variables
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DATABASE=shopsphere
MYSQL_USERNAME=root
MYSQL_PASSWORD=password
REDIS_HOST=localhost
REDIS_PORT=6379
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
RAZORPAY_KEY_ID=your_key_id
RAZORPAY_KEY_SECRET=your_key_secret
TWILIO_ACCOUNT_SID=your_twilio_sid
TWILIO_AUTH_TOKEN=your_twilio_auth_token
TWILIO_PHONE_NUMBER=your_twilio_phone_number
```
3. **Start required services**
```bash
docker-compose up -d
```
4. **Build and run the application**
```bash
mvn clean install
java -jar product-service/target/product-service.jar
# Repeat for each microservice
```
## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

- Fork the repository
- Create your feature branch (git checkout -b feature/amazing-feature)
- Commit your changes (git commit -m 'Add some amazing feature')
- Push to the branch (git push origin feature/amazing-feature)
- Open a Pull Request
