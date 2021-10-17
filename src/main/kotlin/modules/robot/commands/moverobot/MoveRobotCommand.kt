package modules.robot.commands.moverobot

import libs.ddd.commands.Command
import libs.ddd.domain.valueobjects.ID

class MoveRobotCommand(
    val environmentId: ID,
    val robotId: ID
) : Command()  {
}


