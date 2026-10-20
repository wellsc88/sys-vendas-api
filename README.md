# sys-vendas-api

Multi-module Maven project for a sales management system API.

## Overview

This project provides a modular backend for managing sales operations, including
applications, product catalog, customers, orders, and sales modules.

## Project Structure

```
sys-vendas-api/
├── app         # Main application entry point and orchestration
├── catalog     # Product catalog management
├── customers   # Customer management
├── orders      # Order processing
├── sales       # Sales operations
└── pom.xml     # Parent POM
```

## Technologies

- **Java:** 25
- **Build Tool:** Maven 3.x
- **Spring Boot:** 4.0.8 (via BOM import)
- **Encoding:** UTF-8

## Requirements

- JDK 25
- Maven 3.9+ (or use the Maven wrapper if available)

## Build

Compile the entire project from the repository root:

```bash
mvn clean install
```

To skip tests:

```bash
mvn clean install -DskipTests
```

## Modules

| Module    | Description                                |
|-----------|--------------------------------------------|
| `app`     | Main application entry point                 |
| `catalog` | Product catalog and inventory management   |
| `customers` | Customer registration and management     |
| `orders`  | Order creation, tracking, and fulfillment    |
| `sales`   | Sales records and transactions               |

## Version

The project version is centralized via the `revision` property in the parent POM:

```xml
<revision>1.0.0</revision>
```

## License

This project is for personal/educational use.
