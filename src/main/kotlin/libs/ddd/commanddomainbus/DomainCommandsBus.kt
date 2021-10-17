package libs.ddd.commanddomainbus

import database.RepositoryProvider
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

final class DomainCommandsBus (
    val domainServices : HashMap<String, CommandService<in Command, Throwable>>
) {

    inline fun <reified T : Command, S : CommandService<T, *>>sendCommand(command : T) : Either<out Throwable?, ID> {
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