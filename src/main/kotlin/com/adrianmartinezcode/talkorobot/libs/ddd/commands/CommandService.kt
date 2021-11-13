package com.adrianmartinezcode.talkorobot.libs.ddd.commands

import arrow.core.Either
import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

abstract class CommandService<C : Command, E : Throwable>(
    val repositoryProvider: RepositoryProvider
) {

    abstract fun handle(command: C) : Either<E, ID>
}