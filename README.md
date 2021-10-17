### Compile the project:

All the instructions has been written for unix users:

Clone the repository:
- git clone https://github.com/AdrianMartinezCode/TalkoRobot.git

Go to the folder:
- cd TalkoRobot

If you don't have the gradle program:
 - sudo apt install gradle
   (here you could choose a package manager compatible with your system)

Adjust the version gradle:
- gradle wrapper --gradle-version 6.9
  (this is the version that has been compiled and tested the project)

Compile the project:
- ./gradlew build

Run the project:
- java -jar build/libs/TalkoRobot-1.0-SNAPSHOT.jar
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
