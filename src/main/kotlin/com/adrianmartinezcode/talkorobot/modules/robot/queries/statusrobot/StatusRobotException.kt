package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

sealed class StatusRobotException : Throwable() {
    class RobotNotFoundException : StatusRobotException()
}