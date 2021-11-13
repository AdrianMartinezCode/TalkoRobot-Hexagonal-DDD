package com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot

import arrow.core.extensions.option.foldable.fold
import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class PositionRobotController(
    private val commandBus: DomainCommandsBus
) {

    @PostMapping("/position")
    fun position(@RequestBody positionRobotRequest: PositionRobotRequest) : PositionRobotResponse {

        return PositionRobotDirectionEnum.fromChar(positionRobotRequest.orientation).fold(
            { throw PositionRobotException.IncorrectOrientationApplicationException() },
            { orientation ->
                val command = PositionRobotCommand(
                    ID(positionRobotRequest.idEnvironment),
                    positionRobotRequest.x,
                    positionRobotRequest.y,
                    orientation
                )
                commandBus.sendCommand(command).fold(
                    { throw it },
                    { id -> PositionRobotResponse(id.value) }
                )
            }
        )

    }
}