package modules.robot.commands.positionrobot

import database.RepositoryProvider
import libs.ddd.commands.CommandService
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.robot.domain.entities.RobotEntity
import modules.robot.domain.valueobjects.Direction
import modules.robot.domain.valueobjects.Position

class PositionRobotService(
    repositoryProvider: RepositoryProvider
): CommandService<PositionRobotCommand, PositionRobotException>(repositoryProvider) {

    override fun handle(command: PositionRobotCommand) : Either<PositionRobotException, ID>{
        val robotRepository = repositoryProvider.getRobotRepository()
        val environmentRepository = repositoryProvider.getEnvironmentRepository()

        return environmentRepository.getEnvironment(command.environmentId).fold(
            { Either.Left(PositionRobotException.EnvironmentNotDefinedException()) },
            { environment ->
                val position = Position(command.posX, command.posY)

                if (!environment.isPositionValid(position))
                    Either.Left(
                        PositionRobotException.InitialPositionOutOfBoundsException()
                    )
                else {
                    val allRobots = robotRepository.getAllRobots()
                    if (RobotEntity.positionCollidesWithOtherRobots(position, allRobots))
                        Either.Left(
                            PositionRobotException.RobotsCollideException()
                        )
                    else {
                        val robot = RobotEntity.create(
                            Direction.getDirection(command.direction),
                            position,
                            command.environmentId
                        )
                        robotRepository.saveRobot(robot)
                        Either.Right(robot.id)
                    }
                }

            }
        )
    }



}