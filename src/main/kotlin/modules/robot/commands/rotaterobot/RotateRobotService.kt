package modules.robot.commands.rotaterobot

import infraestructure.database.RepositoryProvider
import libs.ddd.commands.CommandService
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either

class RotateRobotService(
    repositoryProvider: RepositoryProvider
) : CommandService<RotateRobotCommand, RotateRobotException>(repositoryProvider) {

    override fun handle(command: RotateRobotCommand): Either<RotateRobotException, ID> {
        val repository = repositoryProvider.getRobotRepository()
        return repository.getRobot(command.robotId).fold(
            { Either.Left(RotateRobotException.RobotNotFoundException()) },
            { robot ->
                val newRobot = when(command.rotateDirection) {
                    RotateRobotDirectionEnum.R -> robot.rotateRobotRight()
                    RotateRobotDirectionEnum.L -> robot.rotateRobotLeft()
                }
                repository.saveRobot(newRobot)
                Either.Right(newRobot.id)
            }
        )
    }

}