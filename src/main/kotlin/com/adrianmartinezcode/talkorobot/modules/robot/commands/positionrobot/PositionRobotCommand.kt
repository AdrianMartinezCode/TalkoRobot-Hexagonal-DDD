package com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot

import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

data class PositionRobotCommand(
    val environmentId: ID,
    val posX: Int,
    val posY: Int,
    val direction: PositionRobotDirectionEnum
) : Command() {
}