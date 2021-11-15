package com.adrianmartinezcode.talkorobot.di

import com.adrianmartinezcode.talkorobot.infraestructure.database.RepositoryProvider
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command
import com.adrianmartinezcode.talkorobot.libs.ddd.commands.CommandService
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentCommand
import com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment.CreateEnvironmentService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.moverobot.MoveRobotService
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotCommand
import com.adrianmartinezcode.talkorobot.modules.robot.commands.positionrobot.PositionRobotService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    companion object {
        @Bean
        fun getDomainCommandBus(
            repositoryProvider: RepositoryProvider
        ) : HashMap<String, CommandService<in Command, Throwable>> {
            return hashMapOf(
                Pair(
                    MoveRobotCommand::class.java.name,
                    MoveRobotService(repositoryProvider) as CommandService<in Command, Throwable>
                ),
                Pair(
                    PositionRobotCommand::class.java.name,
                    PositionRobotService(repositoryProvider) as CommandService<in Command, Throwable>
                ),
                Pair(
                    CreateEnvironmentCommand::class.java.name,
                    CreateEnvironmentService(repositoryProvider) as CommandService<in Command, Throwable>
                )
            )
        }
    }
}