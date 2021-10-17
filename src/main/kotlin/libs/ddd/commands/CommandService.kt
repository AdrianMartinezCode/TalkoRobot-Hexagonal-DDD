package libs.ddd.commands

import database.RepositoryProvider
import libs.ddd.domain.valueobjects.ID
import libs.utils.Either

abstract class CommandService<C : Command, E : Throwable>(
    val repositoryProvider: RepositoryProvider
) {

    abstract fun handle(command: C) : Either<E, ID>
}