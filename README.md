# Photography Booking Platform

Photography Booking System
A modern, full-featured application designed to streamline the process of booking and managing photography sessions. This project was developed by Group 6 as part of the Object-Oriented Programming (OOP) module during Year 1 Semester 2 at SLIIT.

The platform focuses on delivering a robust and intuitive experience for both photographers and clients, supporting efficient scheduling, session management, and communication. This web app use Text files to Store data instead of database. but this is work as a Full Stack web App

Project Leader: Dineth Basura


## Overview

This platform empowers users to:
- Register and authenticate securely
- Explore photographer portfolios and profiles
- Schedule and manage photography bookings
- Track payment and booking status
- Access a dedicated admin dashboard for system oversight

## Technology Stack

- **Backend:** Java (Spring Boot or Servlets)
- **Frontend:** HTML, SCSS, CSS
- **Database:** (Add your DBMS, e.g., MySQL, PostgreSQL)
- **Build Tools:** Maven/Gradle
- **Other:** (List other libraries/frameworks as needed)

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven or Gradle
- Node.js & npm (if using for SCSS compilation)
- A relational database (e.g., MySQL)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Dinethbasura/photography_booking.git
   cd photography_booking
   ```

2. **Backend Setup**
   - Import the project into your preferred Java IDE.
   - Configure database connection details in `src/main/resources/application.properties`.
   - Build the project using Maven or Gradle.

3. **Frontend Setup**
   - Compile SCSS files to CSS (if not automated).
   - Ensure static assets (HTML, CSS, JS) are properly served.

4. **Running the Application**
   - Start the backend server from your IDE or using the command line:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Access the platform via `http://localhost:8080` (or your configured port).

## Project Structure

```
photography_booking/
├── src/
│   ├── main/
│   │   ├── java/            # Java source files (controllers, services, models)
│   │   ├── resources/       # Application properties and resources
│   │   └── webapp/          # Frontend assets
│   │       ├── css/
│   │       ├── scss/
│   │       └── html/
│   └── test/                # Test cases
├── pom.xml / build.gradle   # Build configuration
└── README.md
```

## Key Features

- **User Management:** Secure authentication, profile management
- **Photographer Directory:** Search and filter photographers
- **Booking Engine:** Real-time availability, booking requests, confirmations
- **Admin Dashboard:** Booking oversight, user management, analytics
- **Responsive Design:** Works seamlessly on desktop and mobile devices

## Contribution Guidelines

We welcome contributions! To get started:

1. Fork the repository and create your branch:  
   `git checkout -b feature/your-feature`
2. Commit your changes:  
   `git commit -am 'Add some feature'`
3. Push to the branch:  
   `git push origin feature/your-feature`
4. Open a Pull Request

Please ensure your code adheres to our coding standards and includes relevant tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

<sub>Developed with passion by Group 6</sub>
