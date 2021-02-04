# Oisin Coburn - intercom assignment

### Overview
This project is a simple RESTful API created using spring boot. 

Just after the API starts up it will read a list of users from a file and print the name and user_id of the invited users.
(see src/main/java/com/example/demo/DemoApplication.java)


Additionally, after the API has started - the below endpoint will be exposed return a list of users (sorted by user_id) which are in range of the Dublin Intercom office:

GET - http://localhost:8080/invites

Response body

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


##### Importing and Running the project



