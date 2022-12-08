class Day08(input: List<String>) {

    private val forest = Forest(input)

    fun part1() = with(forest) {
        walk().count { it.isVisible() }
    }

    fun part2() = with(forest) {
        walk().maxOf { it.scenicScore() }
    }

}

private typealias Tree = Pair<Int, Int>

private class Forest(input: List<String>) {

    private val Tree.x: Int get() = first
    private val Tree.y: Int get() = second

    private val data = input.map { it.map(Char::digitToInt) }
    private val height = data.size
    private val width = data.first().size

    fun Tree.height() = data[y][x]

    fun walk(): Sequence<Tree> = sequence {
        for (y in 0 until height)
            for (x in 0 until width)
                yield(x to y)
    }

    fun Tree.isVisible(): Boolean {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) return true
        return directions().any { line -> line.none { tree -> tree.height() >= height() } }
    }

    fun Tree.scenicScore(): Long {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) return 0
        return directions().map { it.viewingDistance(height()) }.reduce { acc, i -> acc * i }
    }

    private fun Tree.directions() = listOf<Pair<IntProgression, (Int) -> Tree>>(
        (y - 1 downTo 0) to { x to it }, /*up*/
        (x + 1 until width) to { it to y }, /*right*/
        (y + 1 until height) to { x to it }, /*down*/
        (x - 1 downTo 0) to { it to y }, /*left*/
    ).map { (it, transform) -> it.asSequence().map(transform) }

    private fun Sequence<Tree>.viewingDistance(height: Int) =
        takeWhileInclusive { it.height() < height }.count().toLong()

}