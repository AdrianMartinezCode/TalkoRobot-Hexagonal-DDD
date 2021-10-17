package application.positionaterobot

import libs.application.ApplicationCommandService
import libs.application.ApplicationException
import libs.ddd.commanddomainbus.DomainCommandsBus
import libs.ddd.domain.valueobjects.ID
import modules.robot.commands.positionrobot.PositionRobotCommand
import modules.robot.commands.positionrobot.PositionRobotDirectionEnum
import modules.robot.commands.positionrobot.PositionRobotService

class PositionateRobotApplicationService(
    commandsBus: DomainCommandsBus
) : ApplicationCommandService(commandsBus){


    fun handle(request: PositionateRobotApplicationRequest) : ID {
        val o = request.orientation
        if (o != 'S' && o != 'W' && o != 'N' && o != 'E') throw IncorrectOrientationApplicationException()
        val orientation = when(o) {
            'S' -> PositionRobotDirectionEnum.S
            'N' -> PositionRobotDirectionEnum.N
            'W' -> PositionRobotDirectionEnum.W
            else -> PositionRobotDirectionEnum.E
        }
        val command = PositionRobotCommand(
            request.idEnvironment,
            request.x,
            request.y,
            orientation
        )
        return domainCommandsBus.sendCommand<
            PositionRobotCommand,
            PositionRobotService
        >(command).fold(
            { exc -> throw ApplicationException(exc) },
            { id -> id }
        )
    }
}