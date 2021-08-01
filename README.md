# Modular OAuth2 TimeService

A MSA consisting of a token provider and a secured timeservice.

## About

This is a *modular* version of the [OAuth2-enabled TimeService](https://github.com/kartoffelquadrat/OAuth2SpringBootDemo).
It differs from the original OAuth2-enabled version in so far that it consists of multiple individual REST services, namely:

 * A standard [OAuth2 token provider / validator](TokenService). Runs on *localhost:8085*
 * An access protected [Time-Service](AuthTimeService). Only tells the time if a valid OAuth2 token is provided. Runs on *localhost:8084*
 * A [Bash client](client.sh) and an [HTML/JavaScript](index.html) client that demonstrate token retrieval and access to the time-service using a valid token.

### Power Up Backend

```bash
cd TokenService
mvn clean package; java -jar target/Tokenservice.jar &
cd ..
cd AuthTimeService
mvn clean package; java -jar target/Timeservice-Tokenrequired.jar &
cd ..
```

or

```
./servers.sh
```

### Test Frontend

You can use the [Web-Frontend](index.html) to test CORS access.

Also, you can use the ```client.sh``` script to test unauthorized access and authorized access:  

```bash
./client.sh
Demonstrating unauthorized access using dummy token: "abc123"
curl -X GET http://127.0.0.1:8084/api/time?token=abc123
 > Server Reply: I dont know you! I'm not telling you the time!

Retrieving tokens from Token service using client credentials ("max", "acb123")
curl -s -X POST --user timeservice-client:password-for-timeservice-client "http://127.0.0.1:8085/oauth/token?grant_type=password&username=max&password=abc123"
 > Access_Token: jQ0Sr/GYub/srMXZ395bXuLlXzU=
 > Refresh_Token: kHhpR0Y5xvYm5WZA/CnZbBLrogA=

Using access token to retrieve time from time-service
curl -X GET http://127.0.0.1:8084/api/time?token=jQ0Sr/GYub/srMXZ395bXuLlXzU=
 > Server Reply: 2021-07-28 12:12:59.077
```

## Pull Requests

 * Github: [Kartoffelquadrat](https://github.com/kartoffelquadrat)
 * Webpage: https://www.cs.mcgill.ca/~mschie3
