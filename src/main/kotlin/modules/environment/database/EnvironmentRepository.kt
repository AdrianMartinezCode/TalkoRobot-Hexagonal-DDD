package modules.environment.database

import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import libs.utils.Option
import modules.environment.domain.entities.EnvironmentEntity

interface EnvironmentRepository {
    fun getEnvironment(id: ID) : Option<EnvironmentEntity>
    fun saveEnvironment(environment: EnvironmentEntity) : Either<Any, ID>
}