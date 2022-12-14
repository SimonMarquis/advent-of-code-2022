operator fun String.times(n: Int) = repeat(n)

inline fun <T, R> Iterable<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }

fun <T> Sequence<T>.takeWhileInclusive(predicate: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile { shouldContinue.also { _ -> shouldContinue = predicate(it) } }
}

@JvmName("intProduct")
fun Iterable<Int>.product(): Long = fold(1L, Long::times)

@JvmName("longProduct")
fun Iterable<Long>.product(): Long = fold(1L, Long::times)

fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }
