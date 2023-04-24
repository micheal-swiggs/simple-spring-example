#### Quickstart

Ensure that you have a local instance of postgreSQL running. One way of doing this is by installing docker and running the following:

    docker run --name postgresql -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=dbpassword -p 5432:5432 -d postgres

Make sure that the db credentials are consistent with those in `application.yaml`. Also ensure that the database `carasent_library` exists.

Run `mvn spring-boot:run`. There are three users all with the same password `password` with security implemented using `BasicAuth`. The users are `john`, `user` and `admin`. It's recommended to browse the Postman collection to get an idea of how to make requests.

#### Documentation

Here is a postman collection for the API:
https://go.postman.co/collections/26384743-1300b272-655f-4129-9080-5655d2302d5f

There is also some very basic and unfinished documentation in `src/main/asciidoc/documentation.adoc`.

#### Tests

Integration tests require docker to be installed as PostgreSQL containers are used in testing.

#### Todo

- Add documentation for all endpoints.
- In the documentation for creating a `Book` add documentation for the `borrowed_at` parameter.
- Package documentation so that it can accessible via `localhost:8080`.
- Consider refactoring endpoints with pagination to only the needed fields.
- Add better `404` messages that actually say which fields are wrong.
