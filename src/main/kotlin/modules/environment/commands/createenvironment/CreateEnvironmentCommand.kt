package modules.environment.commands.createenvironment

import libs.ddd.commands.Command

class CreateEnvironmentCommand(
    val limitX: Int,
    val limitY: Int
) : Command() {
}