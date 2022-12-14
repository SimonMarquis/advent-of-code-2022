import kotlin.math.max
import kotlin.math.min

class Day14(input: List<String>) {

    private val simulation = Simulation(input.walls())

    fun part1(): Long = simulation.apply {
        while (simulatePart1().not()) Unit
    }.iterations.dec()

    fun part2(): Long = simulation.apply {
        while (simulatePart2().not()) Unit
    }.iterations

    private fun List<String>.walls() = flatMap {
        it.split(" -> ").windowed(2, step = 1) { (src, dst) ->
            val (srcX, srcY) = src.toPoint()
            val (dstX, dstY) = dst.toPoint()
            when {
                srcX == dstX -> (min(srcY, dstY)..max(srcY, dstY)).map { y -> Point(srcX, y) }
                srcY == dstY -> (min(srcX, dstX)..max(srcX, dstX)).map { x -> Point(x, srcY) }
                else -> error(this)
            }
        }
    }.flatten().toSet()

    private fun String.toPoint() = split(",").map(String::toInt).let { (x, y) -> Point(x, y) }

    private data class Point(val x: Int, val y: Int)

    private class Simulation(walls: Set<Point>, private val source: Point = Point(500, 0)) {

        private enum class Element { Sand, Wall }

        private val data: MutableMap<Point, Element> = walls.foldInPlace(mutableMapOf()) { this[it] = Element.Wall }
        private val limit = walls.maxBy { it.y }.y
        var iterations = 0L
            private set

        private operator fun get(point: Point) = data[point]

        /**
         * @return `true` when sand starts flowing into the abyss, `false` when it settles.
         */
        fun simulatePart1(): Boolean {
            var point = source
            iterations++
            while (true) {
                val new = point.step()
                if (new == point) return false
                if (new.y >= limit) return true
                point = point becomes new
            }
        }

        /**
         * @return `true` when sand comes to rest at [source], `false` when it settles.
         */
        fun simulatePart2(): Boolean {
            var point = source
            iterations++
            while (true) {
                val new = point.step()
                if (new == source) return true
                if (new == point || new.y >= limit + 2) return false
                point = point becomes new
            }
        }

        /**
         * @return `this` if `this` [Point] is stable, a new [Point] otherwise
         */
        private fun Point.step(): Point {
            val down = copy(y = y + 1)
            if (get(down) == null) return down
            val downLeft = down.copy(x = down.x - 1)
            if (get(downLeft) == null) return downLeft
            val downRight = down.copy(x = down.x + 1)
            if (get(downRight) == null) return downRight
            return this
        }

        private infix fun Point.becomes(new: Point): Point = new.apply {
            data.remove(this@becomes)
            data[this] = Element.Sand
        }

        override fun toString(): String {
            val minY = data.minOf { (it, _) -> it.y }.dec()
            val maxY = data.maxOf { (it, _) -> it.y }.inc()
            val minX = data.minOf { (it, _) -> it.x }.dec()
            val maxX = data.maxOf { (it, _) -> it.x }.inc()
            return (minY..maxY).joinToString("\n") { y ->
                (minX..maxX).joinToString("") { x ->
                    when (get(Point(x, y))) {
                        Element.Sand -> "o"
                        Element.Wall -> "#"
                        null -> " "
                    }
                }
            }
        }
    }

}
