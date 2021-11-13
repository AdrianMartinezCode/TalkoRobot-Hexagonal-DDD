package com.adrianmartinezcode.talkorobot.di

import com.adrianmartinezcode.talkorobot.infraestructure.application.defineenvironment.DefineEnvironmentApplicationService
import com.adrianmartinezcode.talkorobot.infraestructure.application.moverobot.MoveRobotApplicationService
import com.adrianmartinezcode.talkorobot.infraestructure.application.positionaterobot.PositionateRobotApplicationService
import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot.StatusRobotQueryHandler

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