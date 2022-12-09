import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 09")
class Day09Test {

    private val sampleInput = readLines("Day09-sample.txt")
    private val sampleInput2 = readLines("Day09-sample2.txt")
    private val actualInput = readLines("Day09.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 13,
            actual = Day09(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 6522,
            actual = Day09(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example 1`() = assertEquals(
            expected = 1,
            actual = Day09(sampleInput).part2(),
        )

        @Test
        fun `Matches example 2`() = assertEquals(
            expected = 36,
            actual = Day09(sampleInput2).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 2717,
            actual = Day09(actualInput).part2(),
        )

    }

}