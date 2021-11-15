package com.adrianmartinezcode.talkorobot.modules.robot.domain.entities

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.baseclasses.AggregateRoot
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position
import java.awt.Robot

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

        fun positionCollidesWithOtherRobots(position: Position, robots: List<RobotEntity>) : Boolean {
            return robots.fold(false) { acc, v ->
                acc || v.robotIsInThisPosition(position)
            }
        }
    }
    fun collideWithOtherRobots(robots: List<RobotEntity>) : Boolean {
        return RobotEntity.positionCollidesWithOtherRobots(
            properties.position,
            robots.filter { r -> r != this}
        )

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

    override fun equals(other: Any?): Boolean {
        return other is RobotEntity && other.id == id
    }

}