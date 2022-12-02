class Day02(private val input: List<String>) {

    fun part1() = input.sumOf { round ->
        when (round) {
            "A Y", "B Z", "C X" -> 6 // win
            "A X", "B Y", "C Z" -> 3 // draw
            "A Z", "B X", "C Y" -> 0 // lose
            else -> error(round)
        } + (round.last() - 'X').inc()
    }

    fun part2() = input.sumOf { round ->
        val opponent = (round.first() - 'A').inc()
        (1..3).reversed()
        when (round.last()) {
            'Z' -> 6 /* win */ + (opponent % 3) + 1
            'Y' -> 3 /* draw */ + opponent
            'X' -> 0 /* lose */ + if (opponent == 1) 3 else opponent - 1
            else -> error(round)
        }
    }

}
