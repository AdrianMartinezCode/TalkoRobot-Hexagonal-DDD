package com.adrianmartinezcode.talkorobot.modules.environment.database

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.libs.utils.Option
import com.adrianmartinezcode.talkorobot.modules.environment.domain.entities.EnvironmentEntity

interface IEnvironmentRepository {
    fun getEnvironment(id: ID) : Option<EnvironmentEntity>
    fun saveEnvironment(environment: EnvironmentEntity)
}