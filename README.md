# Oisin Coburn - intercom assignment

### Overview
This project is a simple RESTful API created using spring boot.

It contains 1 endpoint which accepts a list of users and returns the users (sorted by user_id) which are in range of the Dublin Intercom office:

POST - http://localhost:8080/users

Request body

```
[
    {
        "latitude": "52.986375",
        "user_id": 12,
        "name": "Christina McArdle",
        "longitude": "-6.043701"
    }
    ....
    
]
```

### Implementation

##### Source Files

The controller for handling the incoming request can be seen in:

src/main/java/com/example/demo/controller/UserController.java

The service which is responsible for building our list of users which are within range of the dublin office can be seen in:

src/main/java/com/example/demo/service/UserService.java


In order to determine the distance between a user and the intercom Dublin office, I've simply used the Haversine formula -> https://www.igismap.com/haversine-formula-calculate-geographic-distance-earth/

the implementation of this can be seen in UserService.getGreatCircleDistanceKM()

##### Tests

Unit tests for this project can be found in

src/test/java/com/example/demo/UserTests.java


##### Running the project



