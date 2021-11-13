package com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Either
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentCommand
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.rotaterobot.RotateRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.rotaterobot.RotateRobotService
import org.springframework.beans.factory.annotation.Autowired

final class DomainCommandsBus (
    val domainServices : HashMap<String, CommandService<in Command, Throwable>>
) {

    inline fun <reified T : Command>sendCommand(command : T) : Either<out Throwable, ID> {
        return domainServices[T::class.java.name]!!.handle(command)
    }

    companion object {

        @Autowired
        fun getDomainCommandBus(
            repositoryProvider: RepositoryProvider
        ) : DomainCommandsBus {
            return DomainCommandsBus(
                hashMapOf(
                    Pair(
                        MoveRobotCommand::class.java.name,
                        MoveRobotService(repositoryProvider) as CommandService<in Command, Throwable>
                    ),
                    Pair(
                        PositionRobotCommand::class.java.name,
                        PositionRobotService(repositoryProvider) as CommandService<in Command, Throwable>
                    ),
                    Pair(
                        RotateRobotCommand::class.java.name,
                        RotateRobotService(repositoryProvider) as CommandService<in Command, Throwable>
                    ),
                    Pair(
                        CreateEnvironmentCommand::class.java.name,
                        CreateEnvironmentService(repositoryProvider) as CommandService<in Command, Throwable>
                    )
                )
            )
        }


    }
}