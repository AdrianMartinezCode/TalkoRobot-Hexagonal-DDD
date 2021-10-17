package modules.environment.commands.createenvironment

import infraestructure.database.RepositoryProvider
import libs.ddd.commands.CommandService
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import modules.environment.domain.entities.EnvironmentEntity
import modules.environment.errors.EnvironmentOutOfBoundsException

class CreateEnvironmentService(
    repositoryProvider: RepositoryProvider
) : CommandService<CreateEnvironmentCommand, EnvironmentOutOfBoundsException>(repositoryProvider) {

    override fun handle(command: CreateEnvironmentCommand): Either<EnvironmentOutOfBoundsException, ID> {
        val repository = repositoryProvider.getEnvironmentRepository()

        if (!EnvironmentEntity.isValidUpperCoordinates(command.limitX, command.limitY)) return Either.Left(
            EnvironmentOutOfBoundsException()
        )
        val entity = EnvironmentEntity.create(command.limitX, command.limitY)

        repository.saveEnvironment(entity)

        return Either.Right(entity.id)
    }
}