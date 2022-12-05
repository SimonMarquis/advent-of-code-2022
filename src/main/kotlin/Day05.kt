import java.lang.System.lineSeparator

class Day05(input: String) {

    private val stacks: List<ArrayDeque<Char>>
    private val moves: List<Move>

    init {
        val (crates, procedure) = input.split(lineSeparator() * 2)

        stacks = crates.lines().asReversed().drop(1).foldInPlace(
            initial = List(size = crates.lines().last().split(" ").last().toInt()) { ArrayDeque() }
        ) { line: String ->
            line.chunked(4).forEachIndexed { index, crate /*[?]*/ ->
                get(index).addFirst(crate[1].takeIf(Char::isLetter) ?: return@forEachIndexed)
            }
        }

        moves = procedure.lines().map {
            it.split(" ")
                .mapNotNull(String::toIntOrNull)
                .let { (amount, start, end) -> Move(amount, start.dec(), end.dec()) }
        }
    }

    fun part1(): String = moves.foldInPlace(stacks) { (amount, start, end) ->
        repeat(amount) {
            val pick = get(start).removeFirst()
            get(end).addFirst(pick)
        }
    }.top()

    fun part2() = moves.foldInPlace(stacks) { (amount, start, end) ->
        val picks = get(start).takeAndRemove(amount)
        get(end).addAll(0, picks)
    }.top()

    data class Move(val amount: Int, val start: Int, val end: Int)

    private fun List<ArrayDeque<Char>>.top() = joinToString("") { it.first().toString() }

    private fun ArrayDeque<Char>.takeAndRemove(n: Int) = take(n).also { repeat(n) { removeFirst() } }

}

