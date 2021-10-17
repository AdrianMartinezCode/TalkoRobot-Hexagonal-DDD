package libs.ddd.domain.valueobjects

/**
 * A class that supports the value of a direction, must have one of these values {-1, 0, 1}
 */
class DirectionValue(
    val value: Int
) {
    init {
    }

    fun toNegative() : DirectionValue {
        return DirectionValue(value*(-1))
    }

    companion object {
        fun Positive() : DirectionValue {
            return DirectionValue(1)
        }
        fun Neutral() : DirectionValue {
            return DirectionValue(0)
        }
        fun Negative() : DirectionValue {
            return DirectionValue(-1)
        }
    }
}