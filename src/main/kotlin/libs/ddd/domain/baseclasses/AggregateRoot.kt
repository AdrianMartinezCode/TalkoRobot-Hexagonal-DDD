package libs.ddd.domain.baseclasses

import libs.ddd.domain.domainevents.DomainEvent
import libs.ddd.domain.valueobjects.ID

abstract class AggregateRoot<T>(
    id: ID,
    val properties: T
) : Entity(id) {

//    private val domainEvents: List<DomainEvent<T>> = listOf()
}