package com.adrianmartinezcode.talkorobot.modules.robot.database

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Option
import com.adrianmartinezcode.talkorobot.modules.robot.domain.entities.RobotEntity

interface IRobotRepository {
    fun getRobot(id: ID) : Option<RobotEntity>
    fun saveRobot(robotEntity: RobotEntity)
    fun getAllRobots() : List<RobotEntity>
}