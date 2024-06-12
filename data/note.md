## APIs

### /api/customers

- **Access:** Only users with the role of `ADMIN` can access this endpoint.
- **Description:** This endpoint provides operations related to customers.

### Other Services

The following services are available for users with the role of `USER`:

- **/api/contacts**
    - **Description:** Provides operations related to contacts.

- **/api/employees**
    - **Description:** Provides operations related to employees.

- **/api/account**
    - **Description:** Provides operations related to user accounts.

## Getting Started

To use the application, follow these steps:

1. **Registration:**
    - Users need to register using the appropriate endpoint.
    - Required fields include `firstName`, `lastName`, `username`, `password`, `email`, `phone`, and `role`.

2. **Login:**
    - After registration, users must log in to access the APIs.
    - Use the login endpoint to authenticate and receive tokens (`accessToken` and `refreshToken`).

3. **Accessing APIs:**
    - Based on the role assigned during registration, users can access the corresponding APIs.
    - Only users with the `ADMIN` role can access `/api/customers`.
    - Users with the `USER` role can access `/api/contacts`, `/api/employees`, and `/api/account`.

## Authentication and Authorization

- **Authentication:** Uses JWT (JSON Web Token) for authentication.
- **Authorization:** Controls access to endpoints based on user roles.

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- JWT (JSON Web Token)
- Hibernate

## Setup Instructions

1. Clone the repository.
2. Configure the application properties (`application.properties` or `application.yml`) with database and other configurations.
3. Run the application.
4. Access the endpoints based on the role and functionality required.

## Contributors

- List of contributors if applicable.

## License

This project is licensed under the [License Name] License - see the LICENSE.md file for details.