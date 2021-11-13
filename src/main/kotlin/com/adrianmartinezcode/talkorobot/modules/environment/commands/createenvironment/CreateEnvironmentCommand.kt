package com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment

import com.adrianmartinezcode.talkorobot.libs.ddd.commands.Command

class CreateEnvironmentCommand(
    val limitX: Int,
    val limitY: Int
) : Command() {
}