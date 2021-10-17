package application.defineenvironment

import libs.application.ApplicationCommandService
import libs.application.ApplicationException
import libs.ddd.commanddomainbus.DomainCommandsBus
import libs.ddd.domain.valueobjects.ID
import modules.environment.commands.createenvironment.CreateEnvironmentCommand
import modules.environment.commands.createenvironment.CreateEnvironmentService

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