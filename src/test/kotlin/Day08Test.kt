import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 08")
class Day08Test {

    private val sampleInput = readLines("Day08-sample.txt")
    private val actualInput = readLines("Day08.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 21,
            actual = Day08(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 1845,
            actual = Day08(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 8,
            actual = Day08(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 230112,
            actual = Day08(actualInput).part2(),
        )

    }

}