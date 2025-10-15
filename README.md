<h1 align="center">🚀 Smart-Rental</h1>

### 📖 Table of Contents
- [📖 Table of Contents](#-table-of-contents)
- [📝 Introduction](#-introduction)
- [🏗️ Architecture \& Technologies](#️-architecture--technologies)
- [⚙️ Installation](#️-installation)
  - [Requirements](#requirements)
  - [Steps](#steps)
- [🔧 Configuration](#-configuration)
- [▶️ Running the Project](#️-running-the-project)
- [📂 Project Structure](#-project-structure)
- [📡 API Documentation](#-api-documentation)
- [✅ Testing](#-testing)
- [🤝 Contributing](#-contributing)
- [👨‍💻 Authors](#-authors)

### 📝 Introduction
A detailed overview of the project:  
- Context / Problem being solved  
- Key features  
- Target users  

### 🏗️ Architecture & Technologies
List all major components and tools used in the project:  

- **Programming Language**: Java 21
- **Framework**: Spring Boot 3.5.6 (REST APIs)  
- **Database**: MySQL 8.0 (with JPA/Hibernate ORM)  
- **Cache**: Redis (for session & caching)  
- **Build Tool**: Maven, npm
- **Auth**: Spring Security, JWT
- **API Docs**: Swagger / OpenAPI 3  
<!-- - **Deployment**: Docker + Docker Compose  
- **Monitoring**: Prometheus + Grafana  
- **Logging**: Logback + ELK stack (optional)   -->

### ⚙️ Installation
#### Requirements

- Java 17 or higher
- Apache Maven 3.9.9
- MySQL 8.0.40
- Docker v25.0.3(optional, for containerized setup)
- Node v18.18.0
- React 19.1.0

#### Steps

- spring boot config: 
    ``` mvn clean package ```
  

### 🔧 Configuration


### ▶️ Running the Project
- Backend: 

    ```
    mvn spring-boot:run
    ```

- Frontend:

    ``` 
    npm run dev 
    ```

### 📂 Project Structure
``` 
    SmartRental
    ├── SmartRental/
    |   ├── account/
    |   ├── auth/
    |   ├── configuration/
    |   ├── contract/
    |   ├── exception/
    |   ├── general/
    |   ├── hostel/
    |   ├── jwt/
    |   ├── notification/
    |   ├── revenue/
    |   ├── stage/
    |   ├── tenant/
    |   ├── vehicle/
    |   ├── SmartRentalApplication.java

    ├── frontend/
    |   ├── public/
    |   ├── src/
    |   |   ├── api/
    |   |   ├── api/
    |   |   ├── components/
    |   |   ├── lib/
    |   |   ├── views/
    |   ├── package-lock.json
    |   ├── package.json
    |   ├── postcss.config.json
    |   ├── tsconfig.json

```


### 📡 API Documentation
    👉 http://localhost:8009/swagger-ui.html

    👉 Endpoints
        - Authentication
            - POST /api/auth/login → Authenticate user
            - POST /api/auth/register → Create new user
        - Accounts
            - GET /api/v1/account → Get all account
            - GET /api/v1/account/{id} → Get account by ID
            - PUT /api/v1/account/{id} → Update account
            - DELETE /api/v1/account/{id} → Delete account
        - Vehicle
            - GET /api/v1/vehicle → Get all vehicle
            - GET /api/v1/vehicle/{id} → Get vehicle by ID
            - PUT /api/v1/vehicle/{id} → Update vehicle
            - DELETE /api/v1/vehicle/{id} → Delete vehicle
            - Patch /api/v1/vehicle/{id} → Update partial vehicle
        - Home
            - GET /api/v1/account → Get all account
            - GET /api/v1/account/{id} → Get account by ID
            - PUT /api/v1/account/{id} → Update account
            - DELETE /api/v1/account/{id} → Delete account
        - Stage
            - GET /api/v1/contractStage → Get all contractStage
            - POST /api/auth/contractStage → Create new user
            - GET /api/v1/contractStage/{id} → Get contractStage by ID
            - PUT /api/v1/contractStage/{id} → Update contractStage
            - DELETE /api/v1/contractStage/{id} → Delete contractStage
          
            - GET /api/v1/roomStage → Get all roomStage
            - POST /api/auth/contractStage → Create new roomStage
            - GET /api/v1/roomStage/{id} → Get roomStage by ID
            - PUT /api/v1/roomStage/{id} → Update roomStage
            - DELETE /api/v1/roomStage/{id} → Delete roomStage
        - Tenant
            - GET /api/v1/tenant → Get all tenant
            - GET /api/v1/tenant/{id} → Get tenant by ID
            - POST /api/v1/tenant → Create new tenant
            - PUT /api/v1/tenant/{id} → tenant account
            - DELETE /api/v1/tenant/{id} → tenant account
### ✅ Testing

        npm run build 
        mvn test

<!-- ### ☁️ Deployment
    - Deployment methods:
        + Docker Compose
        + Cloud platforms: AWS ECS, AWS CloudFront, AWS S3
    - CI/CD integration:
        + GitHub Actions for build & test
        + GitLab CI / Jenkins for enterprise pipelines    

### 📊 Monitoring & Logging
    - Metrics exposed at /actuator/prometheus

    - Logging with Logback in JSON format

    - Dashboards with Grafana (requests, latency, error rates) -->

### 🤝 Contributing
    1. Fork the repository
    2. Create a feature branch (git checkout -b feature/awesome-feature)
    3. Commit changes (git commit -m "Add awesome feature")
    4. Push (git push origin feature/awesome-feature)
    5. Open a Pull Request

### 👨‍💻 Authors
    Dinh Le - Fullstack Developer - [https://github.com/dinhle2701]

<!-- ### 📜 License -->

