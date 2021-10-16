package libs.ddd.domain.domainevents

abstract class DomainEventHandler{
    abstract fun <T>handle(event: DomainEvent<T>)
    fun listen() {
        DomainE
    }
}