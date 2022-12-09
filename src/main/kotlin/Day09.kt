import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09(input: List<String>) {

    private val operations = input.asSequence()
        .map { it.split(" ") }
        .flatMap { (direction, number) -> List(number.toInt()) { direction } }

    fun part1(): Int {
        var head = origin
        var tail = head
        val visited = mutableSetOf(tail)
        operations.forEach { direction ->
            head = head.step(direction)
            tail = tail.follow(head)
            visited += tail
        }
        return visited.size
    }

    fun part2(): Int {
        val knots = MutableList(10) { origin }
        val visited = mutableSetOf(knots.last())
        operations.forEach { direction ->
            knots.forEachIndexed { index, knot ->
                knots[index] = when (index) {
                    0 -> knot.step(direction)
                    else -> knot.follow(knots[index - 1])
                }
            }
            visited += knots.last()
        }
        return visited.size
    }

    private fun Point.step(direction: String) = when (direction) {
        "U" -> x to y + 1
        "R" -> x + 1 to y
        "D" -> x to y - 1
        "L" -> x - 1 to y
        else -> error(direction)
    }

    private fun Point.follow(other: Point): Point {
        val dx = other.x - x
        val dy = other.y - y
        if (dx.absoluteValue <= 1 && dy.absoluteValue <= 1) return this
        return x + dx.sign to y + dy.sign
    }

}

private typealias Point = Pair<Int, Int>

private val Point.x: Int get() = first
private val Point.y: Int get() = second
private val origin = 0 to 0
