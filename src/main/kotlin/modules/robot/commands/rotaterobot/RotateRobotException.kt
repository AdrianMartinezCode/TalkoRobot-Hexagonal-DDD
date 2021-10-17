package modules.robot.commands.rotaterobot

sealed class RotateRobotException : Throwable() {
    class RobotNotFoundException : RotateRobotException()
}