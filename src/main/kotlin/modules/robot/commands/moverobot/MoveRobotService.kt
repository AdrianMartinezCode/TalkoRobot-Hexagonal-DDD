package modules.robot.commands.moverobot

import infraestructure.database.RepositoryProvider
import libs.ddd.commands.CommandService
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.robot.domain.entities.RobotEntity

class MoveRobotService(
    repositoryProvider: RepositoryProvider
): CommandService<MoveRobotCommand, MoveRobotException>(repositoryProvider) {

    override fun handle(command: MoveRobotCommand): Either<MoveRobotException, ID> {
        val robotRepository = repositoryProvider.getRobotRepository()
        val environmentRepository = repositoryProvider.getEnvironmentRepository()

        return environmentRepository.getEnvironment(command.environmentId).fold(
            { Either.Left(MoveRobotException.EnvironmentNotDefinedException())},
            { env ->
                robotRepository.getRobot(command.robotId).fold(
                    { Either.Left(MoveRobotException.RobotNotFoundException())},
                    { robot ->
                        val newPosition = robot.getNextMoveRobot()
                        val allRobots = robotRepository.getAllRobots()
                        if (RobotEntity.positionCollidesWithOtherRobots(newPosition, allRobots))
                            Either.Left(MoveRobotException.RobotsCollideException())
                        else if (env.isPositionValid(newPosition))
                            Either.Left(MoveRobotException.RobotOutOfBoundsException())
                        else {
                            val newRobot = robot.moveRobot()
                            // it only updates internally the position
                            robotRepository.saveRobot(newRobot)
                            Either.Right(newRobot.id)
                        }
                    }
                )
            }
        )
    }



}