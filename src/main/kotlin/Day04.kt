class Day04(raw: List<String>) {

    private val input = raw.asSequence().map {
        it.split(",", "-").map(String::toInt).let { (a, b, c, d) -> a..b to c..d }
    }

    fun part1() = input.count { (a, b) -> a in b || b in a }

    fun part2() = input.count { (a, b) -> a intersect b || b intersect a }

    private operator fun IntRange.contains(other: IntRange): Boolean = other.first in this && other.last in this
    private infix fun IntRange.intersect(other: IntRange): Boolean = other.first in this || other.last in this

}
