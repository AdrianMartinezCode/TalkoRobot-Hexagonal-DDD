package modules.robot.database

import libs.ddd.domain.valueobjects.ID
import libs.utils.Option
import modules.robot.domain.entities.RobotEntity

interface IRobotRepository {
    fun getRobot(id: ID) : Option<RobotEntity>
    fun saveRobot(robotEntity: RobotEntity)
    fun getAllRobots() : List<RobotEntity>
}