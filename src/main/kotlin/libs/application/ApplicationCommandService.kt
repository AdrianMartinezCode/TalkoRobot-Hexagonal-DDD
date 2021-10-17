package libs.application

import libs.ddd.commanddomainbus.DomainCommandsBus

abstract class ApplicationCommandService(
    val domainCommandsBus: DomainCommandsBus
) {
}