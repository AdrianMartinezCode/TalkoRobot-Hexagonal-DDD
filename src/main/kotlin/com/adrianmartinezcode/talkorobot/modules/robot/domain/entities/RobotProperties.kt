package com.adrianmartinezcode.talkorobot.modules.robot.domain.entities

import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Direction
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position

data class RobotProperties(
    val direction: Direction,
    val position: Position
) {
}