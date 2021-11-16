



# NEW Version (with rest api)

All observations at the OLD version are valid, the unique change about the old version is:
- The commands to compile and executed are changed
- The api specified at the document are changed, now is a rest api with the same modus operandi.

### Commands to execute (overwriting determined steps):

5 - Compile the project:
- ./gradlew bootJar

6 - Run the project:
- java -jar build/libs/TalkoRobot-0.0.1-SNAPSHOT.jar


### API Specification
We can speak with the application with throught https requests, for example, we can use the postman client to ease the work.


## POST - Define an environment:
In this request we will define the size of the environment, the resultant identifier are the identifier of scenario that we will positionate the robots, every robot must be in a particular scenario. We can create multiple scenarios.
```
/createenvironment
```

Body Request:
```json
{
  "limitX": 10,
  "limitY": 10
}
```
Body Response:
```json
{
  "id": "VIrWGh6eTH8SVwjRdcRj"
}
```


## POST - Position the robot
In this request we will positionate the robot at certain coordinates and orientation and the resulting body is the identifier of robot that we will use to operate with them. We can create multiple robots in multiple scenarios.
```
/position
```
Body Request:
```json
{
  "idEnvironment": "VIrWGh6eTH8SVwjRdcRj",
  "x": 4,
  "y": 5,
  "orientation": "N" // orientation in {"N","S","E","W"}
}
```
Body Response:
```json
{
  "id": "yfvV6HJwLhJEK2MWRNuV"
}
```

## POST - Move the robot
In this request we can move the robot or rotate in one line of orders. The id of response are the same id of the robot specified.
```
/move
```
Body Request:
```json
{
  "robotId": "dZq0uD375Tn85DwPnbmu",
  "order": "MMMRRMMMMLLMMMMRRMM" // every character must be in {"M","R","L"}
}
```
Body Response:
```json
{
  "id": "dZq0uD375Tn85DwPnbmu"
}
```

## GET - Query the status of a robot
In this request we can query the state of a certain robot specified by id.
```
/status?id={robotId}
```
Query params:
- id: the id of the robot to query the status.
Body Response:
```json
{
  "x": 6,
  "y": 7,
  "orientation": "S"
}
```


# OLD Version (without restapi)

### Compile the project:

All the instructions has been written for unix users:

1 - Clone the repository:
- git clone https://github.com/AdrianMartinezCode/TalkoRobot.git

2 - Go to the folder:
- cd TalkoRobot

3 - If you don't have the gradle program:
 - sudo apt install gradle
   (here you could choose a package manager compatible with your system)

4 - Adjust the version gradle:
 - gradle wrapper --gradle-version 6.9
   (this is the version that has been compiled and tested the project)

5 - Compile the project:
- ./gradlew build

6 - Run the project:
- java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
  (the specified path is the compiled jar)

And you can write the inputs of the program.


### Considerations

I considered a solution that EnvironmentEntity has the position of all robots and, with the help of an EventBus, every time the robot moves, the position was updated by an EventDomain, but this solution, first, need a check that if the robot can continue the move, a classic implementation of event bus not contemplate a checks in real-time (from my point of view), second, the check only performs when the action was occurred, the checks must be perform on the first section of a DomainService.

I have not implemented an EventBus with events because in the two contexts has no side effects between them (with the solution proposed above they would exists).

Omitted patterns for simplicity:

    - Unit of Work with transactions
    - Domain Event Bus
    - Mapping to BD pattern (the Storage stores the complete entity), but
        no have effects to the Domain Layer because is transparent and can
        it implemented later.

This is not a complete Hexagonal Architecture, is a very simplified version of them.

### Testing

I'm not implemented the all possible tests, the implementation of tests only it has been given at one entity of the domain and at one Domain Service.
The tested classes have been:
- RobotEntity
- MoveRobotService

These two classes are the ones with the most complexity at this project.
To execute the testing you must followed the above steps to install gradle.
To execute (being at the root of project):
- ./gradlew check

If any test fails these program exists with exit code different to 0 (and you can integrate CI/CD on other sites).
