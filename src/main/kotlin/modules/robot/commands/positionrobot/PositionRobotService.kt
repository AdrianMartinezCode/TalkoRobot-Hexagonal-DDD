package modules.robot.commands.positionrobot

import infraestructure.database.RepositoryProvider
import libs.ddd.commands.CommandService
import libs.exceptions.ElementNotFoundException
import libs.utils.Either
import modules.robot.database.RobotRepository
import modules.robot.domain.entities.RobotEntity
import modules.robot.errors.InitialPositionOutOfBoundsException

class PositionRobotService(
    repositoryProvider: RepositoryProvider
): CommandService(repositoryProvider) {

    fun handle(command: PositionRobotCommand) : Either<InitialPositionOutOfBoundsException, Nothing>{
        val robotRepository = repositoryProvider.getRobotRepository()
        val environmentRepository = repositoryProvider.getEnvironmentRepository()
        environmentRepository.getEnvironment(command.environmentId).fold(
            { throw ElementNotFoundException() },
            { environment ->
                robotRepository.getRobot(command.robotContext.robotId).fold(
                    { throw ElementNotFoundException() },
                    { robot ->
                        RobotEntity.
                        environment.isPositionValid()
                    }
                )
            }
        )
    }

}