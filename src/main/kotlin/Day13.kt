class Day13(val input: List<String>) {

    fun part1(): Int = input.windowed(size = 2, step = 3) { (l, r) -> (l to r).parse() }
        .withIndex().filter { it.value }
        .sumOf { it.index.inc() }

    fun part2(): Int = TODO()

    private fun String.element(index: Int): IndexedValue<Element?> = when (getOrNull(index)) {
        null -> IndexedValue(index, null)
        '[' -> IndexedValue(index + 1, Start)
        ']' -> IndexedValue(index + 1, End)
        ',' -> element(index.inc())
        else -> {
            val number = drop(index).splitToSequence("[", "]", ",").first()
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
                else -> error("$l & $r")
            }
            indexes = indexes.copy(first = lIdx, second = rIdx)
        }
    }

}
