package com.adrianmartinezcode.talkorobot.libs.ddd.domain.baseclasses

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID

abstract class AggregateRoot<T>(
    id: ID,
    val properties: T
) : Entity(id) {

//    private val domainEvents: List<DomainEvent<T>> = listOf()
}