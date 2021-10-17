package modules.robot.commands.positionrobot

import libs.ddd.commands.Command
import libs.ddd.domain.valueobjects.ID

class PositionRobotCommand(
    val environmentId: ID,
    val posX: Int,
    val posY: Int,
    val direction: PositionRobotDirectionEnum
) : Command() {
}