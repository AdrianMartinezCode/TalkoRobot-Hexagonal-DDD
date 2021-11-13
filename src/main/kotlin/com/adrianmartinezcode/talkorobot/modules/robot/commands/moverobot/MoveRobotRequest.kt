package com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot

data class MoveRobotRequest(
    val robotId: String,
    val order: String
) {
}