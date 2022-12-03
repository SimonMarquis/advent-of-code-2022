class Day03(private val input: List<String>) {

    fun part1() = input.sumOf {
        it.chunked(it.length / 2)
            .map(String::toSet)
            .reduce { acc, chars -> acc intersect chars }
            .single()
            .score()
    }

    fun part2() = input.chunked(3).sumOf {
        it.map(String::toSet)
            .reduce { acc, chars -> acc intersect chars }
            .single()
            .score()
    }

    private fun Char.score() = when (this) {
        in 'a'..'z' -> this - 'a' + 1
        in 'A'..'Z' -> this - 'A' + 27
        else -> error(this)
    }

}
