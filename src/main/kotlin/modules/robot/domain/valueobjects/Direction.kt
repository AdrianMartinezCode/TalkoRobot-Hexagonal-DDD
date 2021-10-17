package modules.robot.domain.valueobjects

import libs.ddd.domain.valueobjects.DirectionValue
import modules.robot.commands.positionrobot.PositionRobotDirectionEnum

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
        fun getDirection(
            direction: PositionRobotDirectionEnum
        ) : Direction {
            return when(direction) {
                PositionRobotDirectionEnum.N -> North()
                PositionRobotDirectionEnum.E -> East()
                PositionRobotDirectionEnum.S -> South()
                PositionRobotDirectionEnum.W -> West()
            }
        }

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