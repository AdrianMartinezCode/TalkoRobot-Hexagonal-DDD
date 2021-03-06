package com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus

import arrow.core.Either
import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentCommand
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
final class DomainCommandsBus (
    val domainServices : HashMap<String, CommandService<in Command, Throwable>>
) {

    inline fun <reified T : Command>sendCommand(command : T) : Either<Throwable, ID> {
        return domainServices[T::class.java.name]!!.handle(command)
    }

    companion object {




    }
}