package com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Either
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity

class CreateEnvironmentService(
    repositoryProvider: RepositoryProvider
) : CommandService<CreateEnvironmentCommand, CreateEnvironmentException>(repositoryProvider) {

    override fun handle(command: CreateEnvironmentCommand): Either<CreateEnvironmentException, ID> {
        val repository = repositoryProvider.getEnvironmentRepository()

        if (!EnvironmentEntity.isValidUpperCoordinates(command.limitX, command.limitY)) return Either.Left(
            CreateEnvironmentException.EnvironmentOutOfBoundsException()
        )
        val entity = EnvironmentEntity.create(command.limitX, command.limitY)

        repository.saveEnvironment(entity)

        return Either.Right(entity.id)
    }
}