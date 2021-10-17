
import application.defineenvironment.DefineEnvironmentApplicationRequest
import application.moverobot.MoveRobotApplicationRequest
import application.positionaterobot.PositionateRobotApplicationRequest
import di.Application
import modules.robot.queries.statusrobot.StatusRobotQuery

fun main(args: Array<String>) {

    val app = Application()

    val (upperX, upperY) = readLine()!!.split(' ').map(String::toInt)

    val envId = app.defineEnvironmentApplicationService.handle(
        DefineEnvironmentApplicationRequest(upperX, upperY)
    )

    val charsRobot1 = readLine()!!.split(' ')
    val (xR1, yR1) = charsRobot1.take(2).map(String::toInt)
    val orientationR1 = charsRobot1[2][0]
    val robot1Id = app.positionateRobotApplicationService.handle(
        PositionateRobotApplicationRequest(envId, xR1, yR1, orientationR1)
    )

    val movesR1 = readLine()!!.toList()
    app.moveRobotApplicationService.handle(
        MoveRobotApplicationRequest(robot1Id, envId, movesR1)
    )

    val chars = readLine()!!.split(' ')
    val (x, y) = chars.take(2).map(String::toInt)
    val orientation = chars[2][0]
    val robot2Id = app.positionateRobotApplicationService.handle(
        PositionateRobotApplicationRequest(envId, x, y, orientation)
    )

    val moves = readLine()!!.toList()
    app.moveRobotApplicationService.handle(
        MoveRobotApplicationRequest(robot2Id, envId, moves)
    )

    val statusR1 = app.statusRobotQueryHandler.handle(
        StatusRobotQuery(robot1Id)
    ).fold(
        {throw it},
        {r -> r}
    )
    val statusR2 = app.statusRobotQueryHandler.handle(
        StatusRobotQuery(robot2Id)
    ).fold(
        {throw it},
        {r -> r}
    )

    println(statusR1.x.toString() + ' ' + statusR1.y.toString() + ' ' + statusR1.orientation)
    println(statusR2.x.toString() + ' ' + statusR2.y.toString() + ' ' + statusR2.orientation)
}