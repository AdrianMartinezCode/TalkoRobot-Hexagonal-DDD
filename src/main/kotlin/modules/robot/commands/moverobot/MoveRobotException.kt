package modules.robot.commands.moverobot


sealed class MoveRobotException {
    class RobotsCollideException : MoveRobotException()
    class EnvironmentNotDefinedException : MoveRobotException()
    class RobotNotFoundException : MoveRobotException()
    class RobotOutOfBoundsException : MoveRobotException()
}