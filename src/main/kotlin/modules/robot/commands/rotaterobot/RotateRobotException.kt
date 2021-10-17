package modules.robot.commands.rotaterobot

sealed class RotateRobotException {
    class RobotNotFoundException : RotateRobotException()
}