package infraestructure.database

import libs.ddd.domain.valueobjects.ID
import libs.utils.Either
import libs.utils.Option
import modules.environment.database.IEnvironmentRepository
import modules.environment.domain.entities.EnvironmentEntity
import modules.robot.domain.entities.RobotEntity

class StorageEnvironment : IEnvironmentRepository {

    val bd: HashMap<String, EnvironmentEntity> = hashMapOf()


    override fun getEnvironment(id: ID): Option<EnvironmentEntity> {
        return Option.FromNullable(bd[id.value])
    }

    override fun saveEnvironment(environment: EnvironmentEntity) {
        bd[environment.id.value] = environment
    }


}