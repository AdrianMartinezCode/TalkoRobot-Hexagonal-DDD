package com.adrianmartinezcode.talkorobot.libs.ddd.domain.valueobjects

import java.util.*

class ID(val value: String) {

    companion object {
        /**
         * Is not a UUID but is ok in this context.
         *
         * Extracted from:
         * https://stackoverflow.com/questions/46943860/idiomatic-way-to-generate-a-random-alphanumeric-string-in-kotlin
         */
        fun generate() : ID {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            val str = (1..20)
                .map { allowedChars.random() }
                .joinToString("")
            return ID(str)
        }

        fun generateUUID() : ID {
            return ID(UUID.randomUUID().toString())
        }
    }
}