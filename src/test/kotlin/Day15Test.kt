import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 15")
class Day15Test {

    private val sampleInput = readLines("Day15-sample.txt")
    private val actualInput = readLines("Day15.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 26,
            actual = Day15(sampleInput).part1(y = 10),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 5108096,
            actual = Day15(actualInput).part1(y = 2000000),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 56000011,
            actual = Day15(sampleInput).part2(20),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 10553942650264,
            actual = Day15(actualInput).part2(4000000),
        )

    }

}