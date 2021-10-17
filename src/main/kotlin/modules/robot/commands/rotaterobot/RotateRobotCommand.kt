package modules.robot.commands.rotaterobot

import libs.ddd.commands.Command
import libs.ddd.domain.valueobjects.ID

class RotateRobotCommand(
    val robotId: ID,
    val rotateDirection: RotateRobotDirectionEnum
) : Command() {
}