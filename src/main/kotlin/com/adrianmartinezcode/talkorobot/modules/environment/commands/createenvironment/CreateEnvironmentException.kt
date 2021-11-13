package com.adrianmartinezcode.talkorobot.modules.environment.commands.createenvironment

sealed class CreateEnvironmentException : Throwable()  {
    class EnvironmentOutOfBoundsException : CreateEnvironmentException()
}