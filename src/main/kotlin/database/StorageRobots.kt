package database

import libs.ddd.domain.valueobjects.ID
import libs.utils.Option
import modules.robot.database.IRobotRepository
import modules.robot.domain.entities.RobotEntity

class StorageRobots : IRobotRepository {

    val hashMap: HashMap<String, RobotEntity> = hashMapOf()


    override fun getRobot(id: ID): Option<RobotEntity> {
        return Option.FromNullable(hashMap[id.value])
    }

    override fun saveRobot(robotEntity: RobotEntity) {
        hashMap[robotEntity.id.value] = robotEntity
    }

    override fun getAllRobots(): List<RobotEntity> {
        return hashMap.values.toMutableList()
    }
}