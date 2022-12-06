class Day06(private val input: String) {

    fun part1() = input.windowedSequence(4).indexOfFirst { it.toSet().size == 4 } + 4

    fun part2() = input.windowedSequence(14).indexOfFirst { it.toSet().size == 14 } + 14

}
