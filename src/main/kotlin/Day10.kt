class Day10(input: List<String>) {

    private class Builder(var x: Int = 1) : MutableList<Int> by mutableListOf(x)

    private val cycles = input.foldInPlace(Builder()) {
        add(x)
        if (it == "noop") return@foldInPlace
        add(x)
        x += it.substringAfterLast(" ").toInt()
    }

    fun part1(): Int = cycles
        .mapIndexed { index, it -> index * it }
        .run { slice(20 until size step 40) }
        .sum()

    fun part2(): String = cycles.drop(1)
        .chunked(40)
        .joinToString(separator = "\n") {
            it.withIndex().joinToString(separator = "") { (i, x) ->
                if ((i - x) in -1..1) "#" else "."
            }
        }


}
