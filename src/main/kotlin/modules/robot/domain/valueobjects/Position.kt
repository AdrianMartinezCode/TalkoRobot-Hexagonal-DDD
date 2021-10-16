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
}