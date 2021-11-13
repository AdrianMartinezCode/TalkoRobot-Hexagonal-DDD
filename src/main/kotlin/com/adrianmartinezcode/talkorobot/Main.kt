
import com.adrianmartinezcode.talkorobot.infraestructure.application.defineenvironment.DefineEnvironmentApplicationRequest
import com.adrianmartinezcode.talkorobot.infraestructure.application.moverobot.MoveRobotApplicationRequest
import com.adrianmartinezcode.talkorobot.infraestructure.application.positionaterobot.PositionateRobotApplicationRequest
import com.adrianmartinezcode.talkorobot.di.Application
import com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot.StatusRobotQuery

fun main(args: Array<String>) {

    val app = Application()

    val (upperX, upperY) = readLine()!!.split(' ').map(String::toInt)

    val envId = app.defineEnvironmentApplicationService.handle(
        DefineEnvironmentApplicationRequest(upperX, upperY)
    )

    while (true) {
        val charsRobot1 = readLine()!!.split(' ')
        val (xR1, yR1) = charsRobot1.take(2).map(String::toInt)
        val orientationR1 = charsRobot1[2][0]
        val robot1Id = app.positionateRobotApplicationService.handle(
            PositionateRobotApplicationRequest(envId, xR1, yR1, orientationR1)
        )

        val movesR1 = readLine()!!.toList()
        app.moveRobotApplicationService.handle(
            MoveRobotApplicationRequest(robot1Id, movesR1)
        )

        val statusR1 = app.statusRobotQueryHandler.handle(
            StatusRobotQuery(robot1Id)
        ).fold(
            {throw it},
            {r -> r}
        )

        println(statusR1.x.toString() + ' ' + statusR1.y.toString() + ' ' + statusR1.orientation)
    }
}