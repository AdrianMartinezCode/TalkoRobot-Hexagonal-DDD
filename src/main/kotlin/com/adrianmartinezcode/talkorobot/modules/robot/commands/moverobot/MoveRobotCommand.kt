package com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot

import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

data class MoveRobotCommand(
    val robotId: ID,
    val orders: List<MoveRobotInstructionEnum>
) : Command() {
    enum class MoveRobotInstructionEnum {
        L, M, R
    }
}


