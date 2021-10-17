package modules.robot.queries.statusrobot

import database.RepositoryProvider
import libs.utils.Either

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