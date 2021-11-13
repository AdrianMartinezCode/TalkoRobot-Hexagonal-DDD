package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.utils.Either

class StatusRobotQueryHandler(
    val repositoryProvider: RepositoryProvider
) {

    fun handle(query: StatusRobotQuery) : Either<RobotNotFoundException, StatusRobotQueryResponse> {
        val repository = repositoryProvider.getRobotRepository()
        return repository.getRobot(query.id).fold(
            { Either.Left(RobotNotFoundException())},
            { robot ->
                Either.Right(StatusRobotQueryResponse(
                    robot.properties.position.x,
                    robot.properties.position.y,
                    robot.properties.direction.getOrientation()
                ))
            }
        )
    }
}