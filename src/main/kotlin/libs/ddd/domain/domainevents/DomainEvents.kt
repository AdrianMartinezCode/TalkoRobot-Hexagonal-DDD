package libs.ddd.domain.domainevents

class DomainEvents {

    companion object {
        val subscribers: Map<String, List<DomainEventHandler>> = HashMap()
    }
}