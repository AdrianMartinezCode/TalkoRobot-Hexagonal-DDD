package com.adrianmartinezcode.talkorobot.di

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotService
import com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot.StatusRobotQueryHandler
import org.springframework.context.annotation.ComponentScan

//@ComponentScan
class Application {

//    fun getMoveRobotService( repositoryProvider: RepositoryProvider): MoveRobotService {
//        MoveRobotCommand::class.java.name
//        MoveRobotService(repositoryProvider) as CommandService<in Command, Throwable>
//    }


//    val moveRobotApplicationService : MoveRobotApplicationService
//    val positionateRobotApplicationService: PositionateRobotApplicationService
//    val defineEnvironmentApplicationService: DefineEnvironmentApplicationService
    val statusRobotQueryHandler : StatusRobotQueryHandler

    init {
        val repositoryProvider = RepositoryProvider()

//        val commandsBus = DomainCommandsBus.getDomainCommandBus(repositoryProvider)

//        moveRobotApplicationService = MoveRobotApplicationService(commandsBus)
//        positionateRobotApplicationService = PositionateRobotApplicationService(commandsBus)
//        defineEnvironmentApplicationService = DefineEnvironmentApplicationService(commandsBus)

        statusRobotQueryHandler = StatusRobotQueryHandler(repositoryProvider)

    }

}