package com.adrianmartinezcode.talkorobot.infraestructure.application.defineenvironment

import com.adrianmartinezcode.talkorobot.libs.application.ApplicationCommandService
import com.adrianmartinezcode.talkorobot.libs.application.ApplicationException
import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentCommand
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentService

class DefineEnvironmentApplicationService(
    commandsBus: DomainCommandsBus
) : ApplicationCommandService(commandsBus){

    fun handle(request: DefineEnvironmentApplicationRequest): ID {
        val command = CreateEnvironmentCommand(
            request.limitX,
            request.limitY
        )

        return domainCommandsBus.sendCommand<
            CreateEnvironmentCommand,
            CreateEnvironmentService
        >(command).fold(
            { exc -> throw ApplicationException(exc) },
            { id -> id }
        )
    }
}