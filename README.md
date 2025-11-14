# ShortURL App

A simple URL shortening service built with **Spring Boot**. Generate short URLs from long URLs safely and efficiently.

---

## Features

- Create short URLs using random Base62 codes

---

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Git
- PostgreSQL

### Run Locally

1. Clone the repository:

```bash
git clone https://github.com/marceloxhenrique/url-shortener.git
```
2. Navigate to the project directory

```bash
cd url-shortener
```

3. Install the project
```bash
mvn clean install
```

4. Setup application.properties

```bash
spring.application.name=urlshortener

spring.sql.init.platform=postgres

spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.datasource.url=${POSTGRES_DATA_SOURCE}

url.base=${URL_BASE}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

5. Running the application
```bash
mvn spring-boot:run
```