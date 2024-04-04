# URL Shortener

This is a URL Shortener sample application.


## Specification

This sample application, at the minimum, implements the following requirements.

1. URL Shortening Functionality:
   - Implement an endpoint `POST /shorten` that accepts a long URL as input and returns a short URL.
   - The short URL should be a unique, randomly generated string of 8 alphanumeric characters.
   - The service should store the mapping between the short URL and the original long URL.
2. URL Redirection:
   - Implement an endpoint `GET /{shortUrl}` that redirects the user to the original long URL associated with the given
     short URL.
   - If the short URL is not found in the service's database, return an appropriate HTTP error response.
3. Data Persistence:
   - Use an in-memory database (e.g. H2) or in-memory data structures to store the mapping between short URLs and long
     URLs.
   - Ensure that the storage mechanism can handle concurrent requests and provide efficient lookup and insertion
     operations.


## Building and Running Locally

To build the application (at least JDK 17 is required), run:

```bash
./gradlew build
```

To run the application, run:

```bash
./gradlew bootRun
```

then navigate to http://localhost:8080/, or use the endpoints (at port 8080 by default) as per the requirements
specification above.
