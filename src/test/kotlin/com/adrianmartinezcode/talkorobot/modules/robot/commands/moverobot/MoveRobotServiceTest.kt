package com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot

import arrow.core.orNull
import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotDirectionEnum
import com.adrianmartinezcode.talkorobot.modules.robot.domain.entities.RobotEntity
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

//import org.junit.Test


internal class MoveRobotServiceTest {



    @Test
    fun robotsCollide() {
        val repositoryProvider = RepositoryProvider()
        val moveRobotService = MoveRobotService(
            repositoryProvider
        )


        val envRepo = repositoryProvider.getEnvironmentRepository()
        val env = EnvironmentEntity.create(5, 5)
        envRepo.saveEnvironment(env)

        val repo = repositoryProvider.getRobotRepository()
        val robot1 = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.N),
            Position(0, 0),
            env.id
        )
        val robot2 = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.S),
            Position(0, 1),
            env.id
        )
        repo.saveRobot(robot1)
        repo.saveRobot(robot2)

         moveRobotService.handle(
            MoveRobotCommand(
                robot1.id,
                listOf(MoveRobotCommand.MoveRobotInstructionEnum.M)
            )
        ).fold(
            { exc -> assertEquals(true, exc is MoveRobotException.RobotsCollideException) },
            { assertEquals(false, true) }
        )

    }

    @Test
    fun robotOutOfBound() {
        val repositoryProvider = RepositoryProvider()
        val moveRobotService = MoveRobotService(
            repositoryProvider
        )


        val envRepo = repositoryProvider.getEnvironmentRepository()
        val env = EnvironmentEntity.create(5, 5)
        envRepo.saveEnvironment(env)

        val repo = repositoryProvider.getRobotRepository()
        val robot1 = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.N),
            Position(5, 5),
            env.id
        )
        repo.saveRobot(robot1)


        moveRobotService.handle(
            MoveRobotCommand(
                robot1.id,
                listOf(MoveRobotCommand.MoveRobotInstructionEnum.M)
            )
        ).fold(
            { exc -> assertEquals(true, exc is MoveRobotException.RobotOutOfBoundsException) },
            { assertEquals(false, true) }
        )

    }
}