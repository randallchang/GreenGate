# GreenGate

A modern user management and authentication system built with Spring Boot and Vue.js 3.

## Overview

GreenGate is a full-stack web application that provides user authentication and management capabilities using JWT-based security. It follows a clean architecture pattern with a clear separation between frontend and backend, containerized for easy deployment.

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.5.8
- **Language**: Java 21
- **Build Tool**: Maven
- **Database**: MySQL 8
- **Caching**: Redis (Spring Data Redis)
- **Authentication**: JWT (JJWT 0.12.3)
- **Security**: BCrypt password encryption
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **ORM**: Spring Data JPA with Hibernate

### Frontend
- **Framework**: Vue.js 3.5.25
- **Build Tool**: Vite 7.2.4
- **UI Library**: Element Plus 2.12.0
- **HTTP Client**: Axios 1.13.2
- **Router**: Vue Router
- **Node Version**: ^20.19.0 || >=22.12.0

## Features

- **User Authentication**: JWT-based login system
- **Auto-Registration**: Automatically creates users on first login
- **User Management**: View and manage system users
- **Secure API**: Token-based authorization for all protected endpoints
- **Responsive UI**: Modern interface built with Element Plus
- **API Documentation**: Integrated Swagger UI

## Project Structure

```
GreenGate/
├── frontend/              # Vue.js 3 frontend application
│   ├── src/
│   │   ├── views/        # Vue components (Login, Index, NotFound)
│   │   ├── router/       # Vue Router configuration
│   │   └── util/         # Utilities (Cookie, API)
│   ├── build/            # Build scripts
│   └── package.json
├── backend/              # Spring Boot backend
│   └── src/main/java/io/github/randallchang/
│       ├── controller/   # REST API endpoints
│       ├── service/      # Business logic
│       ├── repository/   # Data access layer
│       ├── model/        # Domain models
│       ├── config/       # Spring configuration
│       ├── interceptor/  # JWT token validation
│       └── util/         # Utilities (JWT)
├── build/                # Docker configuration
├── pom.xml              # Root Maven configuration
├── Makefile             # Build and run commands
└── README.md
```

## Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Node.js 20.19+** or **22.12+**
- **MySQL 8.0**
- **Redis** (optional, for caching)
- **Docker** (for containerized deployment)

## Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE green_gate CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update database credentials in `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/green_gate
spring.datasource.username=root
spring.datasource.password=root
```

## Running Locally

### Option 1: Using Maven and npm (Development)

1. **Start Backend**:
```bash
cd backend
mvn spring-boot:run
```
Backend will run on `http://localhost:8080`

2. **Start Frontend**:
```bash
cd frontend
npm install
npm run dev
```
Frontend will run on `http://localhost:5173`

### Option 2: Using Makefile (Production Build)

Build and run with Docker:
```bash
make build    # Build Docker image
make run      # Run container
```

### Option 3: Using Maven Package

Build the entire application:
```bash
mvn clean package
java -jar backend/target/api.jar
```

The frontend will be embedded in the JAR and served at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /login` - User login (no authentication required)
  ```json
  Request: { "username": "string", "password": "string" }
  Response: { "token": "string" }
  ```

### User Management
- `GET /user/list` - Get all users (requires JWT token)
  ```json
  Response: [{ "id": 1, "username": "user1" }]
  ```

### API Documentation
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI Spec: `http://localhost:8080/v3/api-docs`

## Authentication Flow

1. User submits credentials to `/login`
2. Backend validates credentials (or creates new user if doesn't exist)
3. JWT token generated and returned
4. Frontend stores token in cookie (`green_gate_admin_token`)
5. All subsequent API requests include token in `Authorization` header
6. Backend validates token via `JwtTokenInterceptor`

## Environment Variables

### Frontend (.env files)
```bash
VITE_API_BASE_URL=http://localhost:8080
```

Environment-specific files:
- `.env` - Default configuration
- `.env.development` - Development mode
- `.env.production` - Production build

### Backend (application.properties)
Key configurations:
- Database connection
- JWT secret key
- CORS allowed origins
- Redis connection (if enabled)

## Docker Deployment

The project includes a multi-stage Dockerfile:

1. **Builder Stage**: Builds both frontend and backend
2. **Runtime Stage**: Creates optimized image with only runtime dependencies

```bash
docker build -t green_gate .
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=local green_gate
```

## Security Features

- **Password Encryption**: BCrypt hashing
- **JWT Authentication**: Stateless token-based auth
- **CORS Protection**: Configured allowed origins
- **Request Interception**: Token validation on protected routes
- **Secure Headers**: Spring Security configurations

## Development

### Frontend Development
```bash
cd frontend
npm run dev     # Start dev server
npm run build   # Build for production
npm run preview # Preview production build
```

### Backend Development
```bash
cd backend
mvn spring-boot:run              # Run application
mvn test                         # Run tests
mvn clean package -DskipTests    # Build without tests
```

## Build Process

The frontend build process (`frontend/build/script.sh`):
1. Runs `npm run build` to create production bundle
2. Copies built files to `backend/src/main/resources/static/`
3. Backend serves frontend as static resources

## Configuration

### CORS Configuration
Update allowed origins in `backend/src/main/resources/application.properties`:
```properties
app.cors.allowed-origins=http://localhost:5173
```

### JWT Configuration
Configure JWT secret in `application.properties`:
```properties
jwt.key=your-secret-key-here
```

## Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Ensure MySQL is running
   - Verify database credentials
   - Check if database exists

2. **Frontend Build Failed**
   - Verify Node.js version
   - Clear node_modules and reinstall: `rm -rf node_modules && npm install`

3. **JWT Token Expired**
   - Re-login to get new token
   - Check token expiration settings

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/your-feature`
5. Submit a Pull Request

## License

This project is licensed under the MIT License.

## Contact

- Organization: io.github.randallchang
- Version: 1.0.0
