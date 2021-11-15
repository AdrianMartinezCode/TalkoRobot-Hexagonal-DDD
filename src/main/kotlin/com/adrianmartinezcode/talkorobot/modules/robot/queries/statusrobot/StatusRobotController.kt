package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class StatusRobotController(
    private val statusRobotQueryHandler: StatusRobotQueryHandler
) {

    @GetMapping("/status")
    fun status(@RequestParam("id") idRobot: String) : StatusRobotResponse {
        val query = StatusRobotQuery(
            ID(idRobot)
        )
        return statusRobotQueryHandler.handle(query).fold(
            { throw it },
            { r ->  StatusRobotResponse.from(r) }
        )
    }
}