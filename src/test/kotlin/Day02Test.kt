import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 02")
class Day02Test {

    private val sampleInput = readLines("Day02-sample.txt")
    private val actualInput = readLines("Day02.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 15,
            actual = Day02(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 11449,
            actual = Day02(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 12,
            actual = Day02(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 13187,
            actual = Day02(actualInput).part2(),
        )

    }

}