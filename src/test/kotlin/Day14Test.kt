import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 14")
class Day14Test {

    private val sampleInput = readLines("Day14-sample.txt")
    private val actualInput = readLines("Day14.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 24,
            actual = Day14(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 779,
            actual = Day14(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 93,
            actual = Day14(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 27426,
            actual = Day14(actualInput).part2(),
        )

    }

}