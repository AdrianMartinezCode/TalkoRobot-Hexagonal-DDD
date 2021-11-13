package com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot

import arrow.core.*
import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity
import com.adrianmartinezcode.talkorobot.modules.robot.domain.entities.RobotEntity

class MoveRobotService(
    repositoryProvider: RepositoryProvider
): CommandService<MoveRobotCommand, MoveRobotException>(repositoryProvider) {

    override fun handle(command: MoveRobotCommand): Either<MoveRobotException, ID> {


        val robotRepository = repositoryProvider.getRobotRepository()
        val environmentRepository = repositoryProvider.getEnvironmentRepository()

        return robotRepository.getRobot(command.robotId).fold(
            { Either.Left(MoveRobotException.RobotNotFoundException()) },
            { robot -> environmentRepository.getEnvironment(robot.id).fold(
                { Either.Left(MoveRobotException.EnvironmentNotDefinedException()) },
                { env -> Either.Right(Pair(robot, env)) }
            ) }
        ).flatMap { (robot, env) ->
            val allRobots = robotRepository.getAllRobots()
            command.orders.fold(Either.Right(robot) as Either<MoveRobotException, RobotEntity>) { acc, v ->
                // apply find schema
                acc.flatMap { robot ->
                    when (v) {
                        MoveRobotCommand.MoveRobotInstructionEnum.R -> Either.Right(robot.rotateRobotRight())
                        MoveRobotCommand.MoveRobotInstructionEnum.L -> Either.Right(robot.rotateRobotLeft())
                        MoveRobotCommand.MoveRobotInstructionEnum.M -> {
                            val newPosition = robot.getNextMoveRobot()
                            if (RobotEntity.positionCollidesWithOtherRobots(newPosition, allRobots))
                                Either.Left(MoveRobotException.RobotsCollideException())
                            else if (!env.isPositionValid(newPosition))
                                Either.Left(MoveRobotException.RobotOutOfBoundsException())
                            else
                                Either.Right(robot.moveRobot())
                        }
                    }
                }
            }.flatMap { r ->
                robotRepository.saveRobot(r)
                Either.Right(r.id)
            }
        }
    }



}