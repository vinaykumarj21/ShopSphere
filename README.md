# ShopSphere
MICROSERVICES BASED E-COMMERCE PLATFORM
## Description
Architected a Microservices Based Platform with functionalities like Login , Authorization , Product Catalog , Payment Gateway Integration (Razorpay) , Service Discovery , Notification Service (Email Notification) etc.

Deployed whole Ecosystem on AWS Elastic Beanstalk leveraging RDS for Data Persistence, CloudWatch for efficient Logging and Monitoring.

Implemented Event Driven Email Service using Kafka to allow sending emails at large scale across different services within the Platform.

Implemented powerful sorting , filtering and paging to allow for efficient discovery of products.

Optimized the response time of APIs from ~500 ms to ~20 ms by making effective usage of Caching for static data using Redis Cache.


## Microservices used in the Project
Product Service

User Authorization Service 

Payment Gateway Service

Email Notification Service

Service Discovery Eureka client

## Microservices Architectural Daigram
![Architecture Diagram](https://github.com/vinaykumarj21/ShopSphere/blob/main/ShopSphereArchitecture.png)

## Tools and Frameworks used :
    SpringBoot , SpringCloud , SpringSecurity , MySQL , Hibernate , JPA , Redis , Razorpay Payment Gateway , JUnit , Kafka 
