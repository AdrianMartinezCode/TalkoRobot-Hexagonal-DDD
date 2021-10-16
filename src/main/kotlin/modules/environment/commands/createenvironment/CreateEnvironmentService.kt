package modules.environment.commands.createenvironment

import libs.utils.Either
import modules.environment.domain.entities.EnvironmentEntity
import modules.environment.errors.EnvironmentOutOfBoundsException

class CreateEnvironmentService {
    fun createEnvironment(limitX: Int, limitY: Int): Either<EnvironmentOutOfBoundsException, EnvironmentEntity> {
        if (!EnvironmentEntity.isValidUpperCoordinates(limitX, limitY)) return Either.Left(
            EnvironmentOutOfBoundsException()
        )
        return Either.Right(EnvironmentEntity.create(limitX, limitY))
    }
}