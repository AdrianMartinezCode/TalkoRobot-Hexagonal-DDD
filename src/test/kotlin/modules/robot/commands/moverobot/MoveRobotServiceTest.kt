package modules.robot.commands.moverobot

import database.RepositoryProvider
import modules.environment.domain.entities.EnvironmentEntity
import modules.robot.commands.positionrobot.PositionRobotDirectionEnum
import modules.robot.domain.entities.RobotEntity
import modules.robot.domain.valueobjects.Direction
import modules.robot.domain.valueobjects.Position
import org.junit.Before
import org.junit.Test

import org.junit.jupiter.api.Assertions.*

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
            Position(0, 0)
        )
        val robot2 = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.S),
            Position(0, 1)
        )
        repo.saveRobot(robot1)
        repo.saveRobot(robot2)

        val exc = moveRobotService.handle(
            MoveRobotCommand(
                env.id,
                robot1.id
            )
        ).errorToNullable()
        assertEquals(true, exc is MoveRobotException.RobotsCollideException)
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
            Position(5, 5)
        )
        repo.saveRobot(robot1)

        val exc = moveRobotService.handle(
            MoveRobotCommand(
                env.id,
                robot1.id
            )
        ).errorToNullable()
        assertEquals(true, exc is MoveRobotException.RobotOutOfBoundsException)
    }
}