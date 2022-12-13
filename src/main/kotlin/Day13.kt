class Day13(val input: List<String>) {

    fun part1(): Int = input.windowed(size = 2, step = 3) { (l, r) -> (l to r).parse() }
        .withIndex().filter { it.value }
        .sumOf { it.index.inc() }

    private val dividers = setOf("[[2]]", "[[6]]")
    fun part2(): Long = input.asSequence().filter(String::isNotEmpty).plus(dividers)
        .sortedWith { o1, o2 -> if ((o1 to o2).parse()) -1 else 1 }
        .withIndex().filter { it.value in dividers }
        .map { it.index.inc() }.toList()
        .product()

    private fun String.element(index: Int): IndexedValue<Element?> = when (getOrNull(index)) {
        null -> IndexedValue(index, null)
        '[' -> IndexedValue(index + 1, Start)
        ']' -> IndexedValue(index + 1, End)
        ',' -> element(index.inc())
        else -> drop(index).splitToSequence("[", "]", ",").first().let { number ->
            IndexedValue(index + number.length, Value(number.toInt()))
        }
    }

    private sealed interface Element
    private object Start : Element
    private object End : Element
    private class Value(val int: Int) : Element

    private fun Pair<String, String>.parse(): Boolean {
        var (left, right) = this
        var indexes = 0 to 0
        while (true) {
            val (lIdx, l) = left.element(indexes.first)
            val (rIdx, r) = right.element(indexes.second)
            when {
                // Both values are integers
                l is Value && r is Value -> {
                    when {
                        l.int < r.int -> return true
                        l.int > r.int -> return false
                    }
                }
                // Both values are lists
                l is End && r !is End -> return true
                r is End && l !is End -> return false
                // Mixed types
                l is Start && r is Value -> {
                    right = right.replaceRange(indexes.second, rIdx, "[${r.int}]")
                    continue
                }

                l is Value && r is Start -> {
                    left = left.replaceRange(indexes.first, lIdx, "[${l.int}]")
                    continue
                }
                // Both values are Start or End
                r == l -> Unit
                else -> Unit
            }
            indexes = indexes.copy(first = lIdx, second = rIdx)
        }
    }

}
