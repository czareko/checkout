# Marketplace

### Missing Features from the Business Perspective

	•	Proper exception handling - What happens if we scan a product that doesn’t exist?
	•	Logic for handling multiples - Currently, our rule will be applied only to the exact product quantity.
	•	More rules for one product - This should work even now, but it’s not tested.
	•	Final step in the checkout process - There is no implementation for completing the checkout, such as clearing the cart or generating a receipt.
	•	Stock management - Managing stock levels is missing.

### What could be done better from tech perspective

	•	Expose all business methods as REST services - Ensure all core business functionalities are accessible via REST endpoints.
	•	More consistent data model - Currently, records are mixed with standard entities and Lombok, leading to inconsistency in accessor methods.
	•	CartService data management methods - These should be moved to DBService or a separate dedicated layer for data management.
	•	Additional tests - Add more tests, particularly for the calculation logic, to improve reliability.
	•	Better exception management - Enhance exception handling to ensure clarity and robustness.
	•	Refactor to a clearer architecture - Align the codebase with hexagonal and DDD (Domain-Driven Design) principles for better maintainability.
	•	Add a real DB and Spring Data for data management - Replace in-memory or mock data handling with a real database and leverage Spring Data for ORM.
	•	Refactor for a fully stateless, scalable solution - Ensure the application is stateless to support scalability in distributed environments.
	•	Add Dockerfile and Docker Compose - Provide a Docker setup to run the application locally with ease.
	•	Add Actuator - Integrate Spring Actuator for monitoring and application health checks.
	•	Add security - Implement security measures, such as authentication and authorization, for the exposed APIs.
	•	Build a user interface - Create a frontend to provide a user-friendly way to interact with the application.

### How to run this app?

```shell
./gradlew bootRun
```

#### REST endpoints

```shell
http://localhost:8080/products
http://localhost:8080/product/{name}
http://localhost:8080/product/{name}/priceRules
```

#### Key files to understand 

Tests: src/test/java/id/cezary/checkout/services/CartServiceTest.java

Rest Controller: src/main/java/id/cezary/checkout/api/CheckoutController.java
