package com.adrianmartinezcode.talkorobot.infraestructure.database

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Option
import com.adrianmartinezcode.talkorobot.modules.robot.database.IRobotRepository
import com.adrianmartinezcode.talkorobot.modules.robot.domain.entities.RobotEntity

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