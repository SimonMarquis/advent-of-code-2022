operator fun String.times(n: Int) = repeat(n)

inline fun <T, R> Iterable<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }

fun <T> Sequence<T>.takeWhileInclusive(predicate: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile { shouldContinue.also { _ -> shouldContinue = predicate(it) } }
}
