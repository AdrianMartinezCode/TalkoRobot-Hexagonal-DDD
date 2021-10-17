package modules.robot.domain.entities

import libs.ddd.domain.baseclasses.AggregateRoot
import libs.ddd.domain.valueobjects.ID
import modules.robot.domain.valueobjects.Direction
import modules.robot.domain.valueobjects.Position

class RobotEntity(
    id: ID,
    properties: RobotProperties
) : AggregateRoot<RobotProperties>(id, properties){

    companion object {
        fun create(direction: Direction, position: Position) : RobotEntity {
            return RobotEntity(
                ID.generate(),
                RobotProperties(direction, position)
            )
        }

        fun positionCollidesWithOtherRobots(
            position: Position,
            robots: List<RobotEntity>
        ) : Boolean {
            return robots.map { r ->
                r.robotIsInThisPosition(position)
            }.reduce { acc, v -> acc || v }
        }
    }

    fun getNextMoveRobot() : Position {
        return properties.position.getPositionByDirection(properties.direction)
    }

    fun robotIsInThisPosition(position: Position) : Boolean {
        return this.properties.position.isSamePosition(position)
    }

    fun moveRobot() : RobotEntity {
        return RobotEntity(
            id,
            RobotProperties(
                properties.direction,
                properties.position.getPositionByDirection(properties.direction)
            )
        )
    }

    fun rotateRobotLeft() : RobotEntity {
        return RobotEntity(
            id,
            RobotProperties(
                properties.direction.rotateNegative90Degrees(),
                properties.position
            )
        )
    }

    fun rotateRobotRight() : RobotEntity {
        return RobotEntity(
            id,
            RobotProperties(
                properties.direction.rotateClockwise90Degrees(),
                properties.position
            )
        )
    }

}