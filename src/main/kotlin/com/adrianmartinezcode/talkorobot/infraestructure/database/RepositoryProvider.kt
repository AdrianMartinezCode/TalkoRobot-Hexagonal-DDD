package com.adrianmartinezcode.talkorobot.infraestructure.database

import com.adrianmartinezcode.talkorobot.modules.environment.database.IEnvironmentRepository
import com.adrianmartinezcode.talkorobot.modules.robot.database.IRobotRepository
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class RepositoryProvider {

    private val robotRepository : IRobotRepository
    private val environmentRepository : IEnvironmentRepository

    init {
        robotRepository = StorageRobots()
        environmentRepository = StorageEnvironment()
    }

    companion object {
        @Bean
        fun getInstance() : RepositoryProvider {
            return RepositoryProvider()
        }
    }

    
    fun getRobotRepository() : IRobotRepository {
        return robotRepository
    }
    fun getEnvironmentRepository(): IEnvironmentRepository {
        return environmentRepository
    }
}