package libs.utils

class Option<V>(private val value: V?) {

    companion object {
        fun <V>Some(value: V) : Option<V> {
            return Option(value)
        }
        fun None() : Option<Nothing> {
            return Option(null)
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