# Smart Clinic Management System

## Overview
The Smart Clinic Management System is a Java Spring Boot application designed to manage clinic operations, including patient records, doctor information, appointments, and prescriptions.

## Features
- Patient management
- Doctor management
- Appointment management
- Prescription management
- REST API endpoints
- JWT authentication components
- MySQL database integration
- Automated build using GitHub Actions
- Docker support

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST APIs
- Docker
- GitHub Actions

## Project Structure
- `backend/` — Spring Boot application
- `backend/src/main/java/` — Java source code
- `database/schema.sql` — Database schema
- `schema-design.md` — Database design documentation
- `.github/workflows/maven.yml` — CI workflow
- `backend/Dockerfile` — Docker build configuration

## Setup
1. Install Java 17, Maven, and MySQL.
2. Create a MySQL database for the application.
3. Configure database credentials in the application's configuration.
4. Open a terminal in the `backend` directory.
5. Run `mvn clean package`.
6. Start the application using `mvn spring-boot:run`.

## API Endpoints
The application includes REST controllers for patient, doctor, appointment, authentication, and prescription operations. Refer to the corresponding controller classes for implemented routes.

## Build Verification
GitHub Actions is configured to build the Maven project when changes are pushed or pull requests are opened.

## Author
Student Capstone Project
