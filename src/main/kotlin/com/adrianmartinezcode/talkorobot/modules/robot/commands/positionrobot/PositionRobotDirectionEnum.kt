package com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.adrianmartinezcode.talkorobot.infraestructure.application.positionaterobot.IncorrectOrientationApplicationException

enum class PositionRobotDirectionEnum {
    N, S, E, W;

    companion object {
        fun fromChar(char: Char) : Option<PositionRobotDirectionEnum> {
            if (char != 'S' && char != 'W' && char != 'N' && char != 'E') return None
            val orientation = when(char) {
                'S' -> PositionRobotDirectionEnum.S
                'N' -> PositionRobotDirectionEnum.N
                'W' -> PositionRobotDirectionEnum.W
                else -> PositionRobotDirectionEnum.E
            }
            return Some(orientation)
        }
    }
}