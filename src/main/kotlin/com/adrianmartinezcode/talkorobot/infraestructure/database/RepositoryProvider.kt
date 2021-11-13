package com.adrianmartinezcode.talkorobot.infraestructure.database

import com.adrianmartinezcode.talkorobot.modules.environment.database.IEnvironmentRepository
import com.adrianmartinezcode.talkorobot.modules.robot.database.IRobotRepository

class RepositoryProvider {

    private val robotRepository : IRobotRepository
    private val environmentRepository : IEnvironmentRepository

    init {
        robotRepository = StorageRobots()
        environmentRepository = StorageEnvironment()
    }

    
    fun getRobotRepository() : IRobotRepository {
        return robotRepository
    }
    fun getEnvironmentRepository(): IEnvironmentRepository {
        return environmentRepository
    }
}