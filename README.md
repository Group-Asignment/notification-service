# Notification Service

A microservice that sends order notifications. Part of the **SWST 41062 –
Enterprise Application Development** group project.

## Overview

Notification Service listens to **RabbitMQ** for "order created" events published
by Order Service. When a message arrives, it logs a (mock) notification with the
customer and order details.

```
Order Service ──▶ RabbitMQ (order.created.queue) ──▶ Notification Service (logs notification)
```

There is **no database** — this service only consumes messages and logs.

## Tech Stack

- Java 21, Spring Boot 3.5
- Spring AMQP (RabbitMQ) – message consumption
- Spring Web, Spring Boot Actuator
- JUnit 5
- Maven

## Running Locally

> Requires **RabbitMQ** to be running (started by Order Service's `docker compose`).

```bash
./mvnw spring-boot:run
```

The service starts on **port 8082** and immediately begins listening to the
`order.created.queue`. RabbitMQ connection details are configured via environment
variables with safe local defaults.

When an order is created (by Order Service), this service logs:

```
=================== NEW ORDER NOTIFICATION ===================
Dear customer #7, your order #1 has been CREATED.
Details: Mechanical Keyboard x 3 = 13500.0
==============================================================
```

## Running Tests

```bash
./mvnw test
```

The RabbitMQ listener is disabled during tests, so no broker is required.
