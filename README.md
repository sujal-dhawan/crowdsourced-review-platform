# Crowdsourced Review Platform

## Description
A full-stack crowdsourced review platform where users can add businesses, submit reviews, and view approved reviews. Admin approval is required before reviews are visible.

## Tech Stack
- Java
- Spring Boot
- MySQL
- HTML, JavaScript

## Features
- Add and browse businesses
- Submit reviews (PENDING by default)
- Admin approval workflow
- View approved reviews only

## How to Run
1. Start MySQL server
2. Update database credentials in `application.properties`
3. Run Spring Boot application
4. Open `http://localhost:8080/index.html`

## Notes
Admin actions (review approval) are handled via backend APIs.
