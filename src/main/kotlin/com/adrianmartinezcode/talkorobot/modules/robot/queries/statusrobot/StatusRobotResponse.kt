package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

data class StatusRobotResponse(
    val x: Int,
    val y: Int,
    val orientation: Char
) {

    companion object {
        fun from(statusRobotQueryResponse: StatusRobotQueryResponse) : StatusRobotResponse {
            return StatusRobotResponse(
                statusRobotQueryResponse.x,
                statusRobotQueryResponse.y,
                statusRobotQueryResponse.orientation.getOrientation()
            )
        }
    }
}