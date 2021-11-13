package com.adrianmartinezcode.talkorobot.libs.utils

class Option<V>(private val value: V?) {

    companion object {
        fun <V>Some(value: V) : Option<V> {
            return Option(value)
        }
        fun <V>None() : Option<V> {
            return Option(null)
        }
        fun <V>FromNullable(value: V?) : Option<V> {
            if (value != null) return Some(value)
            return None()
        }
    }

    fun <K>fold(
        onNone: () -> K,
        onSome: (v: V) -> K
    ) : K {
        if (value == null) return onNone();
        return onSome(value);
    }
}