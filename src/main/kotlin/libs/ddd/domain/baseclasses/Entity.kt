package libs.ddd.domain.baseclasses

import libs.ddd.domain.valueobjects.ID

abstract class Entity(
    id: ID
) {
    private val id = id
        get() = field

}