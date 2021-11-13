package com.adrianmartinezcode.talkorobot.infraestructure.database

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Option
import com.adrianmartinezcode.talkorobot.modules.environment.database.IEnvironmentRepository
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity

class StorageEnvironment : IEnvironmentRepository {

    val bd: HashMap<String, EnvironmentEntity> = hashMapOf()

    override fun getEnvironment(id: ID): Option<EnvironmentEntity> {
        return Option.FromNullable(bd[id.value])
    }

    override fun saveEnvironment(environment: EnvironmentEntity) {
        bd[environment.id.value] = environment
    }


}