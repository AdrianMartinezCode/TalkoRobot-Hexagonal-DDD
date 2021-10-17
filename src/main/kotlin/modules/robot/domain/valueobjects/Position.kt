package modules.robot.domain.valueobjects

class Position(
    val x: Int,
    val y: Int
) {

    fun getPositionByDirection(direction: Direction) : Position {
        return Position(
            x + direction.x.value,
            y + direction.y.value
        )
    }

    fun isSamePosition(position: Position) : Boolean {
        return position.x == x && position.y == y
    }

    override fun toString(): String {
        return "X: $x; Y: $y"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Position) return false;
        return other.x == x && other.y == y
    }

}