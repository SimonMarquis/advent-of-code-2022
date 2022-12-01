class Day01(private val input: String) {

    fun part1() = input
        .splitToSequence("\n\n")
        .map { it.lines().sumOf(String::toLong) }
        .max()

    fun part2() = input
        .splitToSequence("\n\n")
        .map { it.lines().sumOf(String::toLong) }
        .sortedDescending()
        .take(3)
        .sum()

}
