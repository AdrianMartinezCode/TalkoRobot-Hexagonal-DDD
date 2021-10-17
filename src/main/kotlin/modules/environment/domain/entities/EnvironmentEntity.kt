package modules.environment.domain.entities

import libs.ddd.domain.baseclasses.AggregateRoot
import libs.ddd.domain.baseclasses.Entity
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.environment.errors.EnvironmentOutOfBoundsException
import modules.robot.domain.entities.RobotEntity
import modules.robot.domain.valueobjects.Position

class EnvironmentEntity(
    id: ID,
    props: EnvironmentProperties
): AggregateRoot<EnvironmentProperties>(id,props){

    companion object {

        val LIMIT_BOTTOM_X = 0
        val LIMIT_BOTTOM_Y = 0

        fun isValidUpperCoordinates(limitX: Int, limitY: Int) : Boolean {
            return LIMIT_BOTTOM_X < limitX && LIMIT_BOTTOM_Y < limitY
        }

        fun create(limitX: Int, limitY: Int) : EnvironmentEntity {
            return EnvironmentEntity(
                ID.generate(),
                EnvironmentProperties(
                    limitX,
                    limitY
                )
            )
        }
    }

//    fun canMoveRobot(robot: RobotEntity) : Boolean {
//        val pos = robot.getNextMoveRobot()
//
//    }

    fun isPositionValid(position: Position) : Boolean {
        println("Input pos: $position")
        println("Limit positions: " + this.properties.toString())
        return position.x >= 0 && position.x <= super.properties.limitX &&
                position.y >= 0 && position.y <= super.properties.limitY
    }
}