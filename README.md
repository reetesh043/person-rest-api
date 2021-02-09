### CRUD REST API
Restful API to perform crud operation.

#### Prerequisites

* Git
* JDK 8 or later
* Maven 3.0 or later
* Spring Boot
* Docker

### Clone
To get started you can simply clone this repository using git:

```
git@github.com:reetesh043/crud-rest-api.git

```

### Deployment

#### Run locally as Spring-Boot application in its own container

```
mvn spring-boot:run
```

#### Run locally as Spring-Boot application Using Docker

1. Build the image.

```
docker build  -t crud-rest-api .
```

2. Check generated image.

```
docker images
```

3.Run the image and start application. It will start application on port 8080.

```
docker run -p 8090:8080 crud-rest-api 
```
