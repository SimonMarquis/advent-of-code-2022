import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 05")
class Day05Test {

    private val sampleInput = readText("Day05-sample.txt")
    private val actualInput = readText("Day05.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = "CMZ",
            actual = Day05(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = "ZSQVCCJLL",
            actual = Day05(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = "MCD",
            actual = Day05(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = "QZFJRWHGS",
            actual = Day05(actualInput).part2(),
        )

    }

}