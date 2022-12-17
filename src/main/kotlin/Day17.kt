import java.lang.System.lineSeparator

class Day17(private val input: String, rocks: String) {

    fun part1(target: Long = 2022L) = Simulation(input.toList(), rocks).apply {
        while (totalRocks != target) simulate()
    }.top().inc()

    fun part2(target: Long = 1_000_000_000_000L): Long = Simulation(input.toList(), rocks).apply {
        while (totalRocks != target) simulate()
    }.top().inc()

    private val rocks = rocks.split(lineSeparator() * 2).map { rock ->
        rock.lines().asReversed().flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, it -> if (it == '#') Point(x.toLong(), y.toLong()) else null }
        }.toSet().let(::Rock)
    }

    private data class Rock(val pieces: Set<Point>) : Set<Point> by pieces
    private data class Point(val x: Long, val y: Long)
    private class Simulation(moves: List<Char>, rocks: List<Rock>, private val width: Int = 7) {

        private val moves = moves.asSequence().repeat().iterator()
        private val rocks = rocks.asSequence().repeat().iterator()
        private val data = mutableSetOf<Point>()
        var totalRocks = 0L
            private set

        fun top(): Long = data.maxOfOrNull { it.y } ?: -1L

        private fun Rock.spawn() = offset(dx = 2L, dy = top().inc() + 3L)

        private fun Rock.offset(dx: Long = 0, dy: Long = 0) = copy(
            pieces = pieces.mapTo(mutableSetOf()) { Point(it.x + dx, it.y + dy) }
        )

        private fun Rock.pushed(c: Char): Rock = when (c) {
            '<' -> offset(dx = -1)
            '>' -> offset(dx = +1)
            'v' -> offset(dy = -1)
            else -> error(c)
        }.let { new ->
            if (new.minOf { it.x } < 0) return this
            if (new.maxOf { it.x } >= width) return this
            if (new.minOf { it.y < 0 }) return this
            if ((new intersect data).minus(this).isNotEmpty()) return this
            return new
        }

        private infix fun Rock.becomes(new: Rock): Rock = new.apply {
            data.removeAll(this@becomes)
            data.addAll(new)
        }

        fun simulate() {
            var rock = rocks.next().spawn()
            totalRocks++
            while (true) {
                rock.pushed(moves.next()).also { if (it != rock) rock = rock becomes it }
                rock.pushed('v').also { if (it != rock) rock = rock becomes it else return }
            }
        }

        override fun toString(): String {
            val minY = data.minOfOrNull { it.y } ?: 0
            val maxY = data.maxOfOrNull { it.y } ?: 0
            val minX = 0L
            val maxX = 6L
            return (maxY downTo minY).joinToString("\n") { y ->
                (minX..maxX).joinToString("") { x ->
                    if (Point(x, y) in data) "#" else "."
                }
            }
        }

    }

}
