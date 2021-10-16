package modules.robot.domain.entities

import modules.robot.domain.valueobjects.Direction
import modules.robot.domain.valueobjects.Position

data class RobotProperties(
    val direction: Direction,
    val position: Position
) {
}