package modules.robot.domain.entities

import libs.ddd.domain.baseclasses.AggregateRoot
import libs.ddd.domain.valueobjects.ID
import modules.robot.domain.valueobjects.Position

class RobotEntity(
    id: ID,
    properties: RobotProperties
) : AggregateRoot<RobotProperties>(id, properties){

    companion object {
        fun create() : RobotEntity {
            return RobotEntity(
                id,
                properties =
            )
        }
    }

    fun getNextMoveRobot() : Position {
        return properties.position.getPositionByDirection(properties.direction)
    }
}