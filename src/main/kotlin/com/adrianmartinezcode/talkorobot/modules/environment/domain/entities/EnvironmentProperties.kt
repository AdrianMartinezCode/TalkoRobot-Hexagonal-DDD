package com.adrianmartinezcode.talkorobot.modules.environment.domain.entities

data class EnvironmentProperties(
    val limitX: Int,
    val limitY: Int
) {
    override fun toString(): String {
        return "X: $limitX; Y: $limitY"
    }
}