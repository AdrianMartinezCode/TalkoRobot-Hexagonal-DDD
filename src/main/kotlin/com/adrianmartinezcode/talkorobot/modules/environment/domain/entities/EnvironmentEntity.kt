package com.adrianmartinezcode.talkorobot.modules.environment.domain.entities

import com.adrianmartinezcode.talkorobot.libs.ddd.domain.baseclasses.AggregateRoot
import com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects.ID
import com.adrianmartinezcode.talkorobot.modules.robot.domain.valueobjects.Position

class EnvironmentEntity(
    id: ID,
    props: EnvironmentProperties
): AggregateRoot<EnvironmentProperties>(id,props){

    companion object {

        val LIMIT_BOTTOM_X = 0
        val LIMIT_BOTTOM_Y = 0

        fun isValidUpperCoordinates(limitX: Int, limitY: Int) : Boolean {
            return LIMIT_BOTTOM_X < limitX && LIMIT_BOTTOM_Y < limitY
        }

        fun create(limitX: Int, limitY: Int) : EnvironmentEntity {
            return EnvironmentEntity(
                ID.generate(),
                EnvironmentProperties(
                    limitX,
                    limitY
                )
            )
        }
    }


    fun isPositionValid(position: Position) : Boolean {
        return position.x >= LIMIT_BOTTOM_X && position.x <= super.properties.limitX &&
                position.y >= LIMIT_BOTTOM_Y && position.y <= super.properties.limitY
    }
}