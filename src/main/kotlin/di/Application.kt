package di

import application.defineenvironment.DefineEnvironmentApplicationService
import application.moverobot.MoveRobotApplicationService
import application.positionaterobot.PositionateRobotApplicationService
import infraestructure.database.RepositoryProvider
import libs.ddd.commanddomainbus.DomainCommandsBus
import modules.robot.queries.statusrobot.StatusRobotQueryHandler

class Application {

    val moveRobotApplicationService : MoveRobotApplicationService
    val positionateRobotApplicationService: PositionateRobotApplicationService
    val defineEnvironmentApplicationService: DefineEnvironmentApplicationService
    val statusRobotQueryHandler : StatusRobotQueryHandler

    init {
        val repositoryProvider = RepositoryProvider()

        val commandsBus = DomainCommandsBus.getDomainCommandBus(repositoryProvider)

        moveRobotApplicationService = MoveRobotApplicationService(commandsBus)
        positionateRobotApplicationService = PositionateRobotApplicationService(commandsBus)
        defineEnvironmentApplicationService = DefineEnvironmentApplicationService(commandsBus)

        statusRobotQueryHandler = StatusRobotQueryHandler(repositoryProvider)

    }

}