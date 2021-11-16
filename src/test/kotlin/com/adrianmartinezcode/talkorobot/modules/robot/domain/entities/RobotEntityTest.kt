package com.adrianmartinezcode.talkorobot.modules.robot.domain.entities

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotDirectionEnum
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position
import org.junit.Before
//import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

//import kotlin.test.assertEquals

internal class RobotEntityTest {

    lateinit var environment: EnvironmentEntity

    @Before
    fun setup() {
        environment = EnvironmentEntity.create(5, 5)
    }

    @Test
    fun getNextMoveRobot() {
        val robotNorth = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.N),
            Position(0, 0),
            ID.generate()
        )
        val newPositionNorth = robotNorth.getNextMoveRobot()
        val supposedNewPositionNorth = Position(0, 1)
        assertEquals(supposedNewPositionNorth, newPositionNorth)

        val robotWest = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.W),
            Position(1, 0),
            ID.generate()
        )
        val newPositionWest = robotWest.getNextMoveRobot()
        val supposedNewPositionWest = Position(0, 0)
        assertEquals(supposedNewPositionWest, newPositionWest)

        val robotEast = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.E),
            Position(1, 0),
            ID.generate()
        )
        val newPositionEast = robotEast.getNextMoveRobot()
        val supposedNewPositionEast = Position(2, 0)
        assertEquals(supposedNewPositionEast, newPositionEast)

        val robotSouth = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.S),
            Position(1, 1),
            ID.generate()
        )
        val newPositionSouth = robotSouth.getNextMoveRobot()
        val supposedNewPositionSouth = Position(1, 0)
        assertEquals(supposedNewPositionSouth, newPositionSouth)
    }

    @Test
    fun robotIsInThisPosition() {
        val position = Position(0, 0)
        val robot = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.N),
            Position(0, 0),
            ID.generate()
        )
        assertEquals(true, robot.robotIsInThisPosition(position))
    }

    @Test
    fun moveRobot() {
        val robotNorth = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.N),
            Position(0, 0),
            ID.generate()
        )
        val robotNorthMoved = robotNorth.moveRobot()
        val supposedNewPositionNorth = Position(0, 1)
        assertEquals(supposedNewPositionNorth, robotNorthMoved.properties.position)

        val robotWest = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.W),
            Position(1, 0),
            ID.generate()
        )
        val robotWestMoved = robotWest.moveRobot()
        val supposedNewPositionWest = Position(0, 0)
        assertEquals(supposedNewPositionWest, robotWestMoved.properties.position)

        val robotEast = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.E),
            Position(1, 0),
            ID.generate()
        )
        val robotEastMoved = robotEast.moveRobot()
        val supposedNewPositionEast = Position(2, 0)
        assertEquals(supposedNewPositionEast, robotEastMoved.properties.position)

        val robotSouth = RobotEntity.create(
            Direction.getDirection(PositionRobotDirectionEnum.S),
            Position(1, 1),
            ID.generate()
        )
        val robotSouthMoved = robotSouth.moveRobot()
        val supposedNewPositionSouth = Position(1, 0)
        assertEquals(supposedNewPositionSouth, robotSouthMoved.properties.position)
    }

    @Test
    fun rotateRobotLeft() {
    }

    @Test
    fun rotateRobotRight() {
    }
}