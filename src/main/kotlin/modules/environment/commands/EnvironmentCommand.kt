package modules.environment.commands

import modules.environment.context.EnvironmentContext

abstract class EnvironmentCommand(
    val environmentContext: EnvironmentContext
) {
}