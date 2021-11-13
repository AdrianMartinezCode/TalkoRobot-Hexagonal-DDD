package com.adrianmartinezcode.talkorobot.infraestructure.application.positionaterobot

import com.adrianmartinezcode.talkorobot.libs.application.ApplicationCommandService
import com.adrianmartinezcode.talkorobot.libs.application.ApplicationException
import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotDirectionEnum
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotService

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