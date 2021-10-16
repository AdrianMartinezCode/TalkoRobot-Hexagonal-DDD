package modules.robot.domain.valueobjects

import libs.ddd.domain.valueobjects.DirectionValue

class Direction(
    val x: DirectionValue,
    val y: DirectionValue
) {

    init {
        val v = x.value + y.value
        assert(v != 1 && v != -1)
    }

    fun rotateClockwise90Degrees() : Direction {
        return Direction(
            y,
            x.toNegative()
        )
    }
    fun rotateNegative90Degrees() : Direction {
        return Direction(
            y.toNegative(),
            x
        )
    }

    companion object {
        fun North() : Direction {
            return Direction(
                DirectionValue.Neutral(),
                DirectionValue.Positive()
            )
        }
        fun South() : Direction {
            return Direction(
                DirectionValue.Neutral(),
                DirectionValue.Negative()
            )
        }
        fun West() : Direction {
            return Direction(
                DirectionValue.Negative(),
                DirectionValue.Neutral()
            )
        }
        fun East() : Direction {
            return Direction(
                DirectionValue.Positive(),
                DirectionValue.Neutral()
            )
        }
    }

}