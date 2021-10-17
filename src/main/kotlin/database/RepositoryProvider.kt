package database

import modules.environment.database.IEnvironmentRepository
import modules.robot.database.IRobotRepository

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