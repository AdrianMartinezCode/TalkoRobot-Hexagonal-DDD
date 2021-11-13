package com.adrianmartinezcode.talkorobot.infraestructure.application.positionaterobot

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

class PositionateRobotApplicationRequest(
    val idEnvironment: ID,
    val x: Int,
    val y: Int,
    val orientation: Char
) {


}