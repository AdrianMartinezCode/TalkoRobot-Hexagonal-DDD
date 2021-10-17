package modules.robot.database

import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import libs.utils.Option
import modules.robot.domain.entities.RobotEntity

interface RobotRepository {
    fun getRobot(id: ID) : Option<RobotEntity>
    fun saveRobot(robotEntity: RobotEntity) : Unit
    fun getAllRobots() : List<RobotEntity>
}