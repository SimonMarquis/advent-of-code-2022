import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 13")
class Day13Test {

    private val sampleInput = readLines("Day13-sample.txt")
    private val actualInput = readLines("Day13.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 13,
            actual = Day13(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 5350,
            actual = Day13(actualInput).part1(),
        )

    }

    @Ignore
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 140,
            actual = Day13(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 0,
            actual = Day13(actualInput).part2(),
        )

    }

}