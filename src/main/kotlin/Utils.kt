operator fun String.times(n: Int) = repeat(n)

inline fun <T, R> Iterable<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }
