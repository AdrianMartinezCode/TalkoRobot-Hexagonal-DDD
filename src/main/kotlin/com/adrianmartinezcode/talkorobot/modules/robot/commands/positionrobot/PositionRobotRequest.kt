package com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

data class PositionRobotRequest(
    val idEnvironment: String,
    val x: Int,
    val y: Int,
    val orientation: Char
) {
}