package com.adrianmartinezcode.talkorobot.modules.robot.domain.entities

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.baseclasses.AggregateRoot
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position

class RobotEntity(
    id: ID,
    properties: RobotProperties,
    val environmentID: ID
) : AggregateRoot<RobotProperties>(id, properties){

    companion object {
        fun create(direction: Direction, position: Position, environmentId: ID) : RobotEntity {
            return RobotEntity(
                ID.generate(),
                RobotProperties(direction, position),
                environmentId
            )
        }

        fun positionCollidesWithOtherRobots(
            position: Position,
            robots: List<RobotEntity>
        ) : Boolean {
            if (robots.isEmpty()) return false
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
            ),
            environmentID
        )
    }

    fun rotateRobotLeft() : RobotEntity {
        return RobotEntity(
            id,
            RobotProperties(
                properties.direction.rotateNegative90Degrees(),
                properties.position
            ),
            environmentID
        )
    }

    fun rotateRobotRight() : RobotEntity {
        return RobotEntity(
            id,
            RobotProperties(
                properties.direction.rotateClockwise90Degrees(),
                properties.position
            ),
            environmentID
        )
    }

}