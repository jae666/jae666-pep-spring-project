# Project: Spring Social media blog API

## Background 

This project is a backend API for a hypothetical social media blog application. It leverages the Spring Boot framework and provides basic features such as user registration, login, message posting, retrieval, update, and deletion. The API interacts with a database to manage user accounts and messages.

The application follows RESTful principles and allows users to interact with the platform by performing CRUD operations on their messages, viewing other users' posts, and updating or deleting their own content.

## Technologies Used
Technologies Used
Spring Boot - version 2.7.0
Spring Data JPA - version 2.7.0
H2 Database (for development)
Maven - version 3.8.4
Java - version 17

## Features
User Registration (POST /register)
User Login (POST /login)
Message Creation (POST /messages)
Message Retrieval (GET /messages)
Message Retrieval by ID (GET /messages/{messageId})
Message Deletion (DELETE /messages/{messageId})
Message Update (PATCH /messages/{messageId})
Retrieve all messages by a specific user (GET /accounts/{accountId}/messages)

## Getting Started
Follow these steps to get your project up and running locally:

## Prerequisites
Make sure you have the following installed:

Java 17
Maven 3.8.4 or higher
Git
## Installation
Clone the repository:

bash
Copy code
git clone https://github.com/jae666/jae666-pep-spring-project
Navigate into the project directory:

bash
Copy code
cd spring-social-media-blog-api
Build the project using Maven:

On Windows:
bash
Copy code
mvn clean install
On Unix (Linux/Mac):
bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
The application will start on localhost:8080.

Usage
After starting the application, you can use the following API endpoints:

POST /register: Register a new user with a username and password.
POST /login: Log in to the system using a registered username and password.
POST /messages: Create a new message.
GET /messages: Retrieve all messages.
GET /messages/{messageId}: Retrieve a specific message by its ID.
DELETE /messages/{messageId}: Delete a message by its ID.
PATCH /messages/{messageId}: Update an existing message by its ID.
GET /accounts/{accountId}/messages: Retrieve all messages posted by a specific user.
Example Request (POST /register)
json
Copy code
{
  "username": "johndoe",
  "password": "password123"
}
Example Response
json
Copy code
{
  "accountId": 1,
  "username": "johndoe",
  "password": "password123"
}
## Contributors
Jae Ma - Initial development

## License
This project uses the following license: MIT License

