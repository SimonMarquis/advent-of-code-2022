class Day11(input: List<String>) {

    private val monkeys: List<Monkey> = input.windowed(size = 6, 7).map {
        Monkey(
            items = it[1].substringAfter("Starting items: ").split(", ")
                .map(String::toLong).let(::ArrayDeque),
            operation = it[2].run {
                val strings = substringAfter("new = old ").split(" ")
                val operation = strings.first().single()
                val number = strings.last().toLongOrNull()
                when (operation) {
                    '+' -> { it -> it + (number ?: it) }
                    '*' -> { it -> it * (number ?: it) }
                    else -> error(operation)
                }
            },
            action = it.slice(3..5).run {
                Monkey.Action(
                    divisor = this[0].substringAfter("Test: divisible by ").toInt(),
                    ifTrue = this[1].substringAfter("If true: throw to monkey ").toInt(),
                    ifFalse = this[2].substringAfter("If false: throw to monkey ").toInt(),
                )
            },
        )
    }

    fun part1(): Long = monkeys
        .rounds(20, relief = 3)
        .businessLevel()

    fun part2(): Long = monkeys
        .rounds(10000, relief = 1, divisor = monkeys.map { it.action.divisor }.fold(1L) { acc, value -> acc * value })
        .businessLevel()

    data class Monkey(
        var inspections: Long = 0L,
        private val items: ArrayDeque<Long> = ArrayDeque(),
        private val operation: (item: Long) -> Long,
        val action: Action,
    ) {

        fun receive(item: Long) = items.add(item)

        fun steps(relief: Long, divisor: Long? = null) = sequence {
            while (items.isNotEmpty()) {
                inspections++
                val item = items.removeFirst()
                var worryLevel = operation(item)
                worryLevel /= relief
                if (divisor != null) worryLevel %= divisor
                yield(action(worryLevel))
            }
        }

        class Action(val divisor: Int, private val ifTrue: Int, private val ifFalse: Int) {
            operator fun invoke(item: Long) = IndexedValue(
                index = if (item % divisor == 0L) ifTrue else ifFalse,
                value = item,
            )
        }
    }

    private fun List<Monkey>.rounds(times: Int, relief: Long, divisor: Long? = null) = repeat(times) {
        forEach { monkey -> monkey.steps(relief, divisor).forEach { this[it.index].receive(it.value) } }
    }.let { this }

    private fun List<Monkey>.businessLevel() = map(Monkey::inspections)
        .sortedDescending()
        .take(2)
        .fold(1L) { acc, value -> acc * value }

}
