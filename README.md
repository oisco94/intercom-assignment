# Oisin Coburn - intercom assignment

### Overview
This project is a simple RESTful API created using spring boot. 

Just after the API starts up it will read a list of users from a file and print the name and user_id of the invited users.
(see src/main/java/com/example/demo/DemoApplication.java)


Additionally, after the API has started - the below endpoint will be exposed which returns a list of users (sorted by user_id) who are in range of the Dublin Intercom office:

GET - http://localhost:8080/users

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


to run without an IDE simply open a terminal in the folder which youd like to open the project

```git clone https://github.com/oisco94/intercom-assignment.git```

```cd intercom-assignment```

```java -jar rest-service-0.0.1-SNAPSHOT.jar```


The above will clone the project and run the executable jar file which will 
start the web service on port 8080.

from there you should see the following output in your terminal 

```
invited users are as follows:
-----------------------------
user_id: 1
user_name: Alice Cahill
-----------------------------
user_id: 2
user_name: Ian McArdle
-----------------------------
user_id: 3
user_name: Jack Enright
-----------------------------
user_id: 4
user_name: Ian Kehoe
-----------------------------
user_id: 5
user_name: Nora Dempsey
-----------------------------
user_id: 6
user_name: Theresa Enright
-----------------------------
user_id: 7
user_name: Frank Kehoe
-----------------------------
user_id: 8
user_name: Eoin Ahearn
-----------------------------
user_id: 9
user_name: Jack Dempsey
-----------------------------
user_id: 10
user_name: Georgina Gallagher
-----------------------------
user_id: 11
user_name: Richard Finnegan
-----------------------------
user_id: 12
user_name: Christina McArdle
-----------------------------
user_id: 13
user_name: Olive Ahearn
-----------------------------
user_id: 14
user_name: Helen Cahill
-----------------------------
user_id: 15
user_name: Michael Ahearn
-----------------------------
user_id: 16
user_name: Ian Larkin
-----------------------------
user_id: 17
user_name: Patricia Cahill
-----------------------------
user_id: 18
user_name: Bob Larkin
-----------------------------
user_id: 19
user_name: Enid Cahill
-----------------------------
user_id: 20
user_name: Enid Enright
-----------------------------
user_id: 21
user_name: David Ahearn
-----------------------------
user_id: 22
user_name: Charlie McArdle
-----------------------------
user_id: 23
user_name: Eoin Gallagher
-----------------------------
user_id: 24
user_name: Rose Enright
-----------------------------
user_id: 25
user_name: David Behan
-----------------------------
user_id: 26
user_name: Stephen McArdle
-----------------------------
user_id: 27
user_name: Enid Gallagher
-----------------------------
user_id: 28
user_name: Charlie Halligan
-----------------------------
user_id: 29
user_name: Oliver Ahearn
-----------------------------
user_id: 30
user_name: Nick Enright
-----------------------------
user_id: 31
user_name: Alan Behan
-----------------------------
user_id: 39
user_name: Lisa Ahearn
-----------------------------
```

and the webservice will be started on port 8080.

the endpoint can be tested using the following curl command (in a new terminal window):

```
curl --request GET http://localhost:8080/users
```
