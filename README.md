# Spring Boot IDMS Integration

This project is designed to fetch data from the IDMS API, store it in a MySQL database, and expose an API endpoint for retrieving the stored data. It ensures clean code practices, efficient data handling, and secure access to the API endpoint.

## Features

Fetch and save account data from the IDMS API.

Prevent duplicate records in the MySQL database.

Secure API endpoint using JWT for data retrieval.

Automatic table creation during application startup.

Integrated Swagger documentation for API testing and exploration.

### Prerequisites

Java 21

Maven

MySQL

Postman (for testing APIs)

Spring Boot (as the application framework)

### Setup Instructions

Clone the Repository

git clone <repository-url>
cd <repository-directory>

Configure MySQL DatabaseUpdate the application.properties file with your MySQL database credentials:
````
# Datasource configuration
spring.datasource.url=${MYSQL_DB_URL:jdbc:mysql://localhost:3306/idms}
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${MYSQL_DB_USER:root}
spring.datasource.password=${MYSQL_DB_PASSWORD:admin}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````
Run the ApplicationUse Maven to build and start the application:

mvn spring-boot:run

### Access the API

Swagger URL: http://localhost:8080/swagger-ui/index.html


### Database Details

The following tables are created automatically during application startup:

app_users: Stores admin user credentials.
accounts: Stores fetched account details from the IDMS API.

### Usage

Fetch and Store DataThe application will automatically fetch account data from the IDMS API and store it in the account_data table.
Retrieve DataUse the /api/accounts endpoint to retrieve vehicle data. Authentication is required with the default credentials:
Username: 
Password: 
Testing with PostmanImport the provided Postman collection into Postman for testing the API endpoints.

### Default Credentials
````
# Admin User Details

application.admin.username=
application.admin.password=
application.admin.email=
application.admin.role=ADMIN
````


### Additional Notes

Ensure MySQL is running before starting the application.


To modify the API behavior, update the properties in application.properties and restart the application.

Swagger provides detailed API documentation and allows testing directly from the browser.

