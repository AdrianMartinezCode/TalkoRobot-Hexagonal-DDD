package modules.robot.commands.positionrobot


sealed class PositionRobotException {
    class RobotsCollideException : PositionRobotException()
    class InitialPositionOutOfBoundsException : PositionRobotException()
    class EnvironmentNotDefinedException : PositionRobotException()
}