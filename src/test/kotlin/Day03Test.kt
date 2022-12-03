import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 03")
class Day03Test {

    private val sampleInput = readLines("Day03-sample.txt")
    private val actualInput = readLines("Day03.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 157,
            actual = Day03(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 8176,
            actual = Day03(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 70,
            actual = Day03(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 2689,
            actual = Day03(actualInput).part2(),
        )

    }

}