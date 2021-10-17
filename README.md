I considered a solution that EnvironmentEntity has the position of all robots and, with the help of an EventBus, every time the robot moves, the position was updated by an EventDomain, but this solution, first, need a check that if the robot can continue the move, a classic implementation of event bus not contemplate a checks in real-time (from my point of view), second, the check only performs when the action was occurred, the checks must be perform on the first section of a DomainService.

I have not implemented an EventBus with events because in the two contexts has no side effects between them (with the solution proposed above they would exists).

Omitted patterns for simplicity:

    - Unit of Work with transactions
    - Domain Event Bus
    - Mapping to BD pattern (the Storage stores the complete entity), but
        no have effects to the Domain Layer because is transparent and can
        it implemented later.

This is not a complete Hexagonal Architecture, is a very simplified version of them.


