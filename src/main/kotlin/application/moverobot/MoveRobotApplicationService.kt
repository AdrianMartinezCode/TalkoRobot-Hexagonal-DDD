package application.moverobot

import libs.application.ApplicationCommandService
import libs.application.ApplicationException
import libs.ddd.commanddomainbus.DomainCommandsBus
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.robot.commands.moverobot.MoveRobotCommand
import modules.robot.commands.moverobot.MoveRobotService
import modules.robot.commands.rotaterobot.RotateRobotCommand
import modules.robot.commands.rotaterobot.RotateRobotDirectionEnum
import modules.robot.commands.rotaterobot.RotateRobotService

class MoveRobotApplicationService (
    commandsBus: DomainCommandsBus
) : ApplicationCommandService(commandsBus){


    fun handle(request: MoveRobotApplicationRequest) : ID {

        request.instructions.map {
            if (it != 'R' && it != 'L' && it != 'M') {
                throw IncorrectDirectionApplicationException()
            } else {
                when (it) {
                    'R' -> MoveRobotApplicationInstructionEnum.R
                    'L' -> MoveRobotApplicationInstructionEnum.L
                    else -> MoveRobotApplicationInstructionEnum.M
                }
            }
        }.map {
            when (it) {
                MoveRobotApplicationInstructionEnum.L, MoveRobotApplicationInstructionEnum.R -> {
                    val command = RotateRobotCommand(
                        request.robotId,
                        if (it == MoveRobotApplicationInstructionEnum.L) RotateRobotDirectionEnum.L else RotateRobotDirectionEnum.R
                    )
                    domainCommandsBus.sendCommand<
                        RotateRobotCommand,
                        RotateRobotService
                    >(command)
                }
                MoveRobotApplicationInstructionEnum.M -> {
                    val command = MoveRobotCommand(
                        request.environmentId,
                        request.robotId
                    )
                    domainCommandsBus.sendCommand<
                        MoveRobotCommand,
                        MoveRobotService
                    >(command)
                }
            }
        }.map {
            it.fold(
                { exc -> throw exc!! },
                { r -> r }
            )
        }

        return request.robotId
    }
}