package com.adrianmartinezcode.talkorobot.libs.application

import com.adrianmartinezcode.talkorobot.libs.ddd.commanddomainbus.DomainCommandsBus

abstract class ApplicationCommandService(
    val domainCommandsBus: DomainCommandsBus
) {
}