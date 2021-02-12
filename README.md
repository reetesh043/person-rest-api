### CRUD REST API
Restful API to perform crud operation.

#### Prerequisites

* Git
* JDK 8 or later
* Maven 3.0 or later
* Spring Boot
* Docker
* H2 in memory database

### API Detail

These are the crud endpoints provided by the api:

* POST /person-rest-api/v1/persons: create a new person/persons
* GET /person-rest-api/v1/persons: retrieve all person details
* GET /person-rest-api/v1/persons/{id}: retrieve a person by id
* PUT /person-rest-api/v1/persons/{id}: update a person by id
* DELETE /person-rest-api/v1/persons/{id}: delete a person by id

#### Request and Responses for each endpoint
1. create person

Request:
```
curl --location --request POST 'http://localhost:9080/person-rest-api/v1/persons' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '{
    "person": [
        {
            "first_name": "John",
            "last_name": "Keynes",
            "age": "29",
            "favourite_colour": "red"
        },
        {
            "first_name": "Sarah",
            "last_name": "Singh",
            "age": "54",
            "favourite_colour": "blue"
        },
                {
            "first_name": "DON",
            "last_name": "Kumar",
            "age": "34",
            "favourite_colour": "blue"
        }
    ]
}'
```

Response: 

201 CREATED

```
{
    "person": [
        {
            "id": 1,
            "first_name": "John",
            "last_name": "Keynes",
            "age": "29",
            "favourite_colour": "red"
        },
        {
            "id": 2,
            "first_name": "Sarah",
            "last_name": "Singh",
            "age": "54",
            "favourite_colour": "blue"
        },
        {
            "id": 3,
            "first_name": "DON",
            "last_name": "Kumar",
            "age": "34",
            "favourite_colour": "blue"
        }
    ]
}
```

2. retrieve all persons

Request:

```
curl --location --request GET 'http://localhost:9080/person-rest-api/v1/persons' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
```

Response:
```

200 OK

{
    "person": [
        {
            "id": 2,
            "first_name": "Sarah",
            "last_name": "Singh",
            "age": "54",
            "favourite_colour": "blue"
        },
        {
            "id": 3,
            "first_name": "DON",
            "last_name": "Kumar",
            "age": "34",
            "favourite_colour": "blue"
        }
    ]
}
```

3. Get person by id

Request:
```
curl --location --request GET 'http://localhost:9080/person-rest-api/v1/persons/2' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw ''
```

Response:
```

200 OK

{
    "person": [
        {
            "id": 2,
            "first_name": "Sarah",
            "last_name": "Singh",
            "age": "54",
            "favourite_colour": "blue"
        }
    ]
}
```

4. Update existing person

Request:
```
curl --location --request PUT 'http://localhost:9080/person-rest-api/v1/persons/1' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Cookie: JSESSIONID.671f16ca=node0xog1p4jt9wqj1qf5spsc515fb0.node0' \
--data-raw '{
    "person": [
        {
            "first_name": "John",
            "last_name": "Keynes",
            "age": "29",
            "favourite_colour": "red"
        }
    ]
}'
```

Response:
```
200 OK

{
    "person": [
        {
            "id": 2,
            "first_name": "John",
            "last_name": "Keynes",
            "age": "29",
            "favourite_colour": "red"
        }
    ]
}
```

5. Delete existing person

Request:
```
curl --location --request DELETE 'http://localhost:9080/person-rest-api/v1/persons/1' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json'
```

Response:
```
200 OK
```
### Clone
To get started you can simply clone this repository using git:

```
git@github.com:reetesh043/person-rest-api.git

```



### Deployment

#### Run locally as Spring-Boot application in its own container

```
mvn spring-boot:run
```

#### Run the application Using Docker

1. Create the package(jar)

```
mvn clean package
```

2. Build the image

```
docker build  -t person-rest-api .
```

3. Check generated image(optional)

```
docker images
```

4.Run the image and start application. It will start application on port 9080

```
docker run -p 9080:9080 person-rest-api 
```

### Health Check

It will provide the status if api is up and running:

```
http://localhost:9080/person-rest-api/v1/actuator/health
```
### Documentation

Api documentation will be available at:

```
http://localhost:9080/person-rest-api/v1/v2/api-docs
```

Api swagger will be  available at:

```
http://localhost:9080/person-rest-api/v1/swagger-ui.html
```

### Postman collection for testing
```
https://github.com/reetesh043/person-rest-api/blob/main/src/main/resources/person-rest-api.postman_collection.json

```
