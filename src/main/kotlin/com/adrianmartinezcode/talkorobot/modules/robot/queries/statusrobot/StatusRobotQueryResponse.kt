package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction

data class StatusRobotQueryResponse(
    val x: Int,
    val y: Int,
    val orientation: Direction
) {
}