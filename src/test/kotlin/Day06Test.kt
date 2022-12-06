import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 06")
class Day06Test {

    private val sampleInput = readText("Day06-sample.txt")
    private val actualInput = readText("Day06.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 11,
            actual = Day06(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 1909,
            actual = Day06(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 26,
            actual = Day06(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 3380,
            actual = Day06(actualInput).part2(),
        )

    }

}