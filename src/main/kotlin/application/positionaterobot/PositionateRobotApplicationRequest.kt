package application.positionaterobot

import libs.ddd.domain.valueobjects.ID

class PositionateRobotApplicationRequest(
    val idEnvironment: ID,
    val x: Int,
    val y: Int,
    val orientation: Char
) {


}