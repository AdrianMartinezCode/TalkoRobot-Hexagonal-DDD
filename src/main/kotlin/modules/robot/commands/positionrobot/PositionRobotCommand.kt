package modules.robot.commands.positionrobot

import libs.ddd.domain.valueobjects.ID
import modules.robot.context.RobotContext
import modules.robot.commands.libs.RobotCommand

class PositionRobotCommand(
    robotContext: RobotContext,
    val environmentId: ID,
    val posX: Int,
    val posY: Int,
    val direction: PositionRobotDirectionEnum
) : RobotCommand(robotContext) {
}