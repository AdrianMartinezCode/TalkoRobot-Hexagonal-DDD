package libs.ddd.commanddomainbus

import infraestructure.database.RepositoryProvider
import libs.ddd.commands.Command
import libs.ddd.commands.CommandService
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.environment.commands.createenvironment.CreateEnvironmentCommand
import modules.environment.commands.createenvironment.CreateEnvironmentService
import modules.robot.commands.moverobot.MoveRobotCommand
import modules.robot.commands.moverobot.MoveRobotService
import modules.robot.commands.positionrobot.PositionRobotCommand
import modules.robot.commands.positionrobot.PositionRobotService
import modules.robot.commands.rotaterobot.RotateRobotCommand
import modules.robot.commands.rotaterobot.RotateRobotService
import kotlin.reflect.KClass

final class DomainCommandsBus (
    val domainServices : HashMap<String, CommandService<in Command, *>>
) {

    inline fun <reified T : Command, S : CommandService<T, *>>sendCommand(command : T) : Either<out Any?, ID> {
        return domainServices[T::class.java.name]!!.handle(command)
    }

    companion object {

        fun getDomainCommandBus(
            repositoryProvider: RepositoryProvider
        ) : DomainCommandsBus {
            return DomainCommandsBus(
                hashMapOf(
                    Pair(
                        MoveRobotCommand::class.java.name,
                        MoveRobotService(repositoryProvider) as CommandService<in Command, Any>
                    ),
                    Pair(
                        PositionRobotCommand::class.java.name,
                        PositionRobotService(repositoryProvider) as CommandService<in Command, Any>
                    ),
                    Pair(
                        RotateRobotCommand::class.java.name,
                        RotateRobotService(repositoryProvider) as CommandService<in Command, Any>
                    ),
                    Pair(
                        CreateEnvironmentCommand::class.java.name,
                        CreateEnvironmentService(repositoryProvider) as CommandService<in Command, Any>
                    )
                )
            )
        }


    }
}