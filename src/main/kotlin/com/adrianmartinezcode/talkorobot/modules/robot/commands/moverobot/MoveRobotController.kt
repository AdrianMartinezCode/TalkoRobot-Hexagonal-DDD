package com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot

import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class MoveRobotController(
    private val commandBus: DomainCommandsBus
) {

    @PostMapping("/move")
    fun move(@RequestBody moveRobotRequest: MoveRobotRequest) : MoveRobotResponse {
        val instructions = moveRobotRequest.order.map {
            if (it != 'R' && it != 'L' && it != 'M') {
                throw MoveRobotException.IncorrectDirectionException()
            } else {
                when (it) {
                    'R' -> MoveRobotCommand.MoveRobotInstructionEnum.R
                    'L' -> MoveRobotCommand.MoveRobotInstructionEnum.L
                    else -> MoveRobotCommand.MoveRobotInstructionEnum.M
                }
            }
        }
        return commandBus.sendCommand(MoveRobotCommand(ID(moveRobotRequest.robotId), instructions)).fold(
            { throw it },
            { r -> MoveRobotResponse(r.value) }
        )

    }

}