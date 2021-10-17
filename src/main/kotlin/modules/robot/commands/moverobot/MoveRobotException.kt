package modules.robot.commands.moverobot


sealed class MoveRobotException : Throwable() {
    class RobotsCollideException : MoveRobotException()
    class EnvironmentNotDefinedException : MoveRobotException()
    class RobotNotFoundException : MoveRobotException()
    class RobotOutOfBoundsException : MoveRobotException()
}