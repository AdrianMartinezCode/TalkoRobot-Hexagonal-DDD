package application.moverobot

import libs.ddd.domain.valueobjects.ID

class MoveRobotApplicationRequest(
    val robotId: ID,
    val environmentId: ID,
    val instructions: List<Char>
) {
}