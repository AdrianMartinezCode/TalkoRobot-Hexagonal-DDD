package libs.utils

/**
 * This class simulates an Either class of FP
 */
class Either<L, R>(private val error: L?, private val result: R?) {

    init {
        assert(error == null && result == null || error != null && result != null)
    }

    companion object {
        fun <L, R>Right(result: R): Either<L, R> {
            return Either(null, result)
        }
        fun <L, R>Left(error: L): Either<L, R> {
            return Either(error, null)
        }
    }

    fun <K>fold(
        onLeft: (error: L) -> K,
        onRight: (result: R) -> K
    ) : K {
        if (this.error != null) return onLeft(this.error)
        return onRight(this.result!!)
    }
}