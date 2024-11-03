# b6-validation-app

A simple app to add a custom validator to validate a regular amount object.

## Running locally

Firstly, run:

```bash
mvn clean install
```

Then you can run the app through:

```bash
mvn spring-boot:run
```

You can send requests to a simple controller to test the validation. This can be done by navigating to the following link in your browser or through Postman.

Link: `http://localhost:8080/calculate`

The controller accepts the `amount` and `frequency` in the query parameters like: `http://localhost:8080/calculate?amount=100&frequency=WEEK`

Example requests can be found in the postman collection in `./postman`.

To use, import the collection into postman and start sending requests!
