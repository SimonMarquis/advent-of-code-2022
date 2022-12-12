class Day12(input: List<String>) {

    private val heightMap = HeightMap(input)
    private val start = heightMap.find('S').single()
    private val end = heightMap.find('E').single()

    fun part1(): Int = with(heightMap) {
        bfs(start = start, end = end)!!
    }

    fun part2(): Int = with(heightMap) {
        find('a').mapNotNull { bfs(start = it, end = end) }.min()
    }

    private class HeightMap(input: List<String>) {

        data class Point(val x: Int, val y: Int)

        private val data = input.map { it.toCharArray() }
        private val height = data.size
        private val width = data.first().size

        fun Point.value() = data[y][x]

        fun find(char: Char) = walk().filter { it.value() == char }

        fun walk(): Sequence<Point> = sequence {
            for (y in 0 until height)
                for (x in 0 until width)
                    yield(Point(x, y))
        }

        private operator fun contains(point: Point) = point.x in 0 until width && point.y in 0 until height

        private fun Point.reachable() = adjacent()
            .filter { it in this@HeightMap }
            .filter { it.value().normalized() - value().normalized() <= 1 }

        private fun Point.adjacent() = listOf(copy(y = y - 1), copy(x = x + 1), copy(y = y + 1), copy(x = x - 1))

        private fun Char.normalized() = when (this) {
            'S' -> 'a'.dec()
            'E' -> 'z'
            else -> this
        }

        fun bfs(start: Point, end: Point): Int? = buildMap {
            val seen = mutableSetOf(start)
            var queue = setOf(start)
            var step = 0
            while (queue.isNotEmpty()) {
                step++
                queue = queue
                    .flatMapTo(mutableSetOf()) { it.reachable() }
                    .filterTo(mutableSetOf()) { it !in seen }
                    .onEach {
                        seen += it
                        putIfAbsent(it, step)
                    }
            }
        }[end]
    }

}
