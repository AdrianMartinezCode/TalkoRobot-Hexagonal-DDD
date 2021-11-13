package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class StatusRobotController(
    private val statusRobotQueryHandler: StatusRobotQueryHandler
) {

    @GetMapping("/status")
    fun status(@RequestBody statusRobotRequest: StatusRobotRequest) : StatusRobotResponse {
        val query = StatusRobotQuery(
            ID(statusRobotRequest.id)
        )
        return statusRobotQueryHandler.handle(query).fold(
            { throw it },
            { r ->  StatusRobotResponse.from(r) }
        )
    }
}