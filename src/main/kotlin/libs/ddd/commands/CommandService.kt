package libs.ddd.commands

import infraestructure.database.RepositoryProvider
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either

abstract class CommandService<C : Command, E>(
    val repositoryProvider: RepositoryProvider
) {

    abstract fun handle(command: C) : Either<E, ID>
}