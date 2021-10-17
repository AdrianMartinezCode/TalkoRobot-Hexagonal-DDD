package modules.robot.commands.positionrobot


sealed class PositionRobotException : Throwable() {
    class RobotsCollideException : PositionRobotException()
    class InitialPositionOutOfBoundsException : PositionRobotException()
    class EnvironmentNotDefinedException : PositionRobotException()
}