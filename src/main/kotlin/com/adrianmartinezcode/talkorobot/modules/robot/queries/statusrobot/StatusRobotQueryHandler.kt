package com.adrianmartinezcode.talkorobot.modules.robot.queries.statusrobot

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.utils.Either
import org.springframework.stereotype.Component

@Component
class StatusRobotQueryHandler(
    private val repositoryProvider: RepositoryProvider
) {

    fun handle(query: StatusRobotQuery) : Either<StatusRobotException, StatusRobotQueryResponse> {
        val repository = repositoryProvider.getRobotRepository()
        return repository.getRobot(query.id).fold(
            { Either.Left(StatusRobotException.RobotNotFoundException())},
            { robot ->
                Either.Right(StatusRobotQueryResponse(
                    robot.properties.position.x,
                    robot.properties.position.y,
                    robot.properties.direction
                ))
            }
        )
    }
}