# Workshop Spring Boot & MongoDB 🍃

This is a professional RESTful API project developed to demonstrate a deep integration between **Spring Boot** and **MongoDB**. The system simulates a social media backend where users can manage profiles, create posts, and engage through comments.

## 🚀 Technologies
* **Java 17**
* **Spring Boot 3**
* **Spring Data MongoDB**
* **MongoDB Atlas**
* **Maven**

## 🏗️ Project Architecture
The project follows a solid layered architecture to ensure maintainability:
* **Domain**: Core entities (`User`, `Post`).
* **DTO (Data Transfer Object)**: Decoupling the database layer from the UI layer (`UserDTO`, `AuthorDTO`, `CommentDTO`).
* **Repository**: Advanced data access using both Query Methods and `@Query` annotations.
* **Service**: Business logic and custom exception handling (e.g., `ObjectNotFoundException`).
* **Resource (REST Controller)**: Standardized API endpoints.

## 🛠️ Key Features

### User Management
* Complete CRUD operations.
* **User's Posts Endpoint**: Retrieve all posts associated with a specific user profile.

### Advanced Searching (The "Full Search")
The project features a powerful multi-criteria search engine:
* **Simple Title Search**: Using both Spring Data naming conventions and custom `@Query` with Regex.
* **Complex Multi-Criteria Query**: A specialized endpoint that filters posts by:
    1. A text string (matching Title, Body, or Comments).
    2. A specific date range (Minimum and Maximum date).

## 📊 Database Modeling
This project showcases strategic NoSQL modeling:
* **Referencing (@DBRef)**: Users and Posts are linked by reference to keep the database lean and avoid massive document sizes.
* **Embedding**: Comments are embedded directly within the Post document (using `CommentDTO`), optimizing performance for retrieving a post and its feedback in a single operation.

## 🔍 API Endpoint Highlight

### Multi-Criteria Search (Full Search)
`GET /posts/fullsearch?text=enjoy&minDate=2018-01-01&maxDate=2018-12-31`

**MongoDB Logic Implementation:**
```json
{ $and: [ 
    { data: { $gte: ?1 } }, 
    { data: { $lte: ?2 } }, 
    { $or: [ 
        { 'title': { $regex: ?0, $options: 'i' } }, 
        { 'body': { $regex: ?0, $options: 'i' } }, 
        { 'comments.text': { $regex: ?0, $options: 'i' } } 
    ] } 
] }