package com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment

import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class CreateEnvironmentController(
    private val commandBus: DomainCommandsBus
) {

    @PostMapping("/createenvironment")
    fun create(@RequestBody createEnvironmentRequest: CreateEnvironmentRequest) : CreateEnvironmentResponse {
        val command = CreateEnvironmentCommand(
            createEnvironmentRequest.limitX,
            createEnvironmentRequest.limitY
        )
        return commandBus.sendCommand(command).fold(
            { throw it },
            { id -> CreateEnvironmentResponse(id.value) }
        )
    }
}