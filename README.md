# student-app

# Pre-requestits
    -JDK 11+
    - Docker
    - Maven
    - docker-compose


## Build on docker
``` 
docker-compose build --no-cache

```

## Run on run
``` 
docker-compose up

```

## Stop the application 
``` 
docker-compose down

```

## Project build with JUnits
``` 
mvn clean package

```

## Access the UI application
``` 
http://localhost:8080/

```

## Access the REST endpoint
``` 
curl -X GET http://localhost:8080/students/Deepak

```
