package modules.robot.domain.entities

import libs.ddd.domain.valueobjects.ID
import modules.robot.domain.valueobjects.Direction
import modules.robot.domain.valueobjects.Position

data class RobotProperties(
    val direction: Direction,
    val position: Position
) {
}