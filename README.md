#### Quickstart

Run `mvn spring-boot:run`. There are three users all with the same password `password` with security implemented using `BasicAuth`. The users are `john`, `user` and `admin`. It's recommended to browse the Postman collection to get an idea of how to make requests.

#### Documentation

Here is a postman collection for the API:
https://go.postman.co/collections/26384743-1300b272-655f-4129-9080-5655d2302d5f

There is also some very basic and unfinished documentation in `src/main/asciidoc/documentation.adoc`.

#### Todo

- Refactor `Book` class into atleast a `BookEntity` and `BookDto` classes.
- Add documentation for all endpoints.
- In the documentation for creating a `Book` add documentation for the `borrowed_at` parameter.
- Package documentation so that it can accessible via `localhost:8080`.
- Consider refactoring endpoints with pagination to only the needed fields.
- Add better `404` messages that actually say which fields are wrong.
