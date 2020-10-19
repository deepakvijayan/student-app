# student-app

# Pre-requestits
    -JDK 11
    - Docker - 18.09.2 +
    - Maven - 3.6 +
    - docker-compose 1.23.2+


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
