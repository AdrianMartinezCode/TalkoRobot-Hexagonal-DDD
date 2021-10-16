package libs.ddd.commands

import infraestructure.database.RepositoryProvider

abstract class CommandService(
    val repositoryProvider: RepositoryProvider
) {
}